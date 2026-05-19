#!/usr/bin/env python3
import argparse
import re
import subprocess
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parent

DEFAULT_MVN = Path("/Users/sulabh/Documents/apache-maven-3.9.16/bin/mvn")
DEFAULT_JAVA_HOME = Path("/Library/Java/JavaVirtualMachines/jdk-26.jdk/Contents/Home")

LOCATOR_FIXES = {
    "div[data-component-type='s-search-result'] h2 a": "div.s-main-slot div[data-component-type='s-search-result'] h2 a, div.s-main-slot .s-result-item h2 a",
    "div[data-component-type='s-search-result']": "div.s-main-slot div[data-component-type='s-search-result'], div.s-main-slot .s-result-item",
    "div.s-main-slot h2 a": "div.s-main-slot div[data-component-type='s-search-result'] h2 a, div.s-main-slot .s-result-item h2 a",
    "div.sc-list-item": "div.sc-list-item, div.sc-list-item-content, div.a-row.sc-list-item",
}

ERROR_PATTERN = re.compile(r"By\.(?P<type>\w+):\s*(?P<value>.+?)(?:\s*\(tried for.*|$)")
LOCATOR_CONSTANT_PATTERN = re.compile(r'public\s+static\s+final\s+By\s+(\w+)\s*=\s*By\.(?:id|cssSelector|xpath|className)\("([^"]*)"\)')


def run_maven(mvn_path: Path, java_home: Path):
    command = [str(mvn_path), "-U", "clean", "test"]
    print(f"Running: {' '.join(command)}")
    env = dict(**subprocess.os.environ)
    env["JAVA_HOME"] = str(java_home)
    result = subprocess.run(command, cwd=ROOT, env=env, capture_output=True, text=True)
    output = result.stdout + "\n" + result.stderr
    return result.returncode, output


def find_failed_locators(output: str):
    locators = set()
    for line in output.splitlines():
        match = ERROR_PATTERN.search(line)
        if match:
            locators.add(match.group("value").strip())
    return locators


def patch_locator(original: str, replacement: str):
    patched_files = []
    locators_dir = ROOT / "src/main/java/com/amazon/framework/locators"
    
    # Only patch locator files in the locators directory
    if locators_dir.exists():
        for java_file in locators_dir.glob("*.java"):
            content = java_file.read_text()
            if f'"{original}"' in content:
                new_content = content.replace(f'"{original}"', f'"{replacement}"')
                if new_content != content:
                    java_file.write_text(new_content)
                    patched_files.append(java_file.relative_to(ROOT))
    return patched_files


def try_fix_locators(locators):
    patched_any = False
    for locator in locators:
        replacement = LOCATOR_FIXES.get(locator)
        if replacement:
            print(f"Applying locator fix: '{locator}' -> '{replacement}'")
            patched_files = patch_locator(locator, replacement)
            if patched_files:
                patched_any = True
                print(f"Patched {len(patched_files)} file(s): {', '.join(str(p) for p in patched_files)}")
            else:
                print(f"Did not find locator literal for: {locator}")
        else:
            print(f"No automatic fix available for: {locator}")
    return patched_any


def main():
    parser = argparse.ArgumentParser(description="Self-healing locator agent for the Amazon BDD framework.")
    parser.add_argument("--mvn-path", default=str(DEFAULT_MVN), help="Path to the mvn executable")
    parser.add_argument("--java-home", default=str(DEFAULT_JAVA_HOME), help="JAVA_HOME for running Maven")
    args = parser.parse_args()

    mvn_path = Path(args.mvn_path)
    java_home = Path(args.java_home)

    if not mvn_path.exists():
        print(f"Maven executable not found at {mvn_path}")
        sys.exit(1)
    if not java_home.exists():
        print(f"JAVA_HOME path not found at {java_home}")
        sys.exit(1)

    code, output = run_maven(mvn_path, java_home)
    if code == 0:
        print("Initial test run passed. No locator repairs needed.")
        sys.exit(0)

    locators = find_failed_locators(output)
    if not locators:
        print("No locator failures detected in the test output.")
        sys.exit(code)

    patched = try_fix_locators(locators)
    if not patched:
        print("No patches were applied. Review the test output and update locator mappings.")
        sys.exit(code)

    print("Re-running tests after applying locator fixes...")
    code, output = run_maven(mvn_path, java_home)
    print(output)
    sys.exit(code)


if __name__ == "__main__":
    main()
