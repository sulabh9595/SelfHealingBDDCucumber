# BDD Cucumber Java Selenium Framework

## Overview
A Java Selenium BDD framework using Cucumber, TestNG, Allure reporting, YAML test data management, and Page Object Model.

## Folder structure
- `src/main/java` - framework utilities and page objects
- `src/test/java` - step definitions and test runner
- `src/test/resources` - feature files, YAML data, properties

## Build and run
1. Install Java 21 and Maven globally.
2. Run:
   ```bash
   mvn clean test
   ```
3. Generate Allure report:
   ```bash
   mvn allure:serve
   ```

## Notes
- `test.properties` contains central configuration values.
- `testdata.yaml` contains reusable test data values.
- The framework uses ChromeDriver via WebDriverManager.

## Self-healing locator agent
Use `self_healing_agent.py` to detect failing locators, apply known locator repairs in page object classes, and rerun the Maven test suite automatically.

Example:
```bash
python3 self_healing_agent.py \
  --mvn-path /Users/sulabh/Documents/apache-maven-3.9.16/bin/mvn \
  --java-home /Library/Java/JavaVirtualMachines/jdk-26.jdk/Contents/Home
```

The agent currently includes fallback mappings for Amazon search result selectors and can be extended with additional locator replacements.
