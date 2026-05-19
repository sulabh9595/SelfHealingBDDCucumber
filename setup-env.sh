#!/bin/bash

# macOS system setup for the BDD Cucumber Java Selenium Framework.
# Run this script after reviewing it manually.

# Install Homebrew if missing:
if ! command -v brew >/dev/null 2>&1; then
  echo "Homebrew not found. Install Homebrew first: https://brew.sh"
  exit 1
fi

# Install Java 21
brew install openjdk@21
sudo ln -sfn /opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-21.jdk

# Install Maven
brew install maven

# Set environment variables for the current user
SHELL_RC="$HOME/.zshrc"
if ! grep -q 'JAVA_HOME' "$SHELL_RC"; then
  cat >> "$SHELL_RC" <<'EOF'
export JAVA_HOME="/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home"
export M2_HOME="/Users/sulabh/Documents/apache-maven-3.9.16"
export PATH="$JAVA_HOME/bin:$M2_HOME/bin:$PATH"
EOF
fi

echo "Java and Maven setup complete. Restart your terminal or run 'source ~/.zshrc'."
