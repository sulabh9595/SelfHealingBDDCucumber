# BDD Cucumber Java Selenium Framework - Enhanced

![GitHub](https://img.shields.io/badge/github-SelfHealingBDDCucumber-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.16-green)
![Cucumber](https://img.shields.io/badge/Cucumber-7.34.3-blue)

A production-ready **Java Selenium BDD framework** using Cucumber, TestNG, Allure reporting, YAML test data management, and Page Object Model with self-healing capabilities.

## 📋 Table of Contents
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running Tests](#running-tests)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Self-Healing Locators](#self-healing-locators)
- [Reporting](#reporting)
- [Contributing](#contributing)

## ✨ Features

✅ **BDD Framework** - Cucumber 7 with TestNG  
✅ **Modern Selenium** - Selenium 4.16 with WebDriverManager  
✅ **Page Object Model** - Clean, maintainable architecture  
✅ **Multi-Environment Support** - Dev, Staging, Production  
✅ **Multiple Browsers** - Chrome, Firefox, Edge support  
✅ **Enhanced Reporting** - Allure Reports with detailed metrics  
✅ **YAML Test Data** - Centralized test data management  
✅ **Self-Healing Locators** - Python agent for automatic locator repair  
✅ **Error Handling** - Custom exception hierarchy  
✅ **CI/CD Integration** - GitHub Actions workflow  
✅ **Code Quality** - SonarQube + CheckStyle integration  
✅ **Code Coverage** - JaCoCo coverage reports  

## 🏗️ Architecture

```
├── src/main/java/com/amazon/framework/
│   ├── pages/              # Page Object Models
│   ├── locators/           # Web element locators
│   ├── utils/              # Framework utilities
│   ├── exceptions/         # Custom exceptions
│   └── ...
├── src/test/java/com/amazon/tests/
│   ├── StepDefinitions.java    # Cucumber step definitions
│   ├── Hooks.java              # Test lifecycle hooks
│   └── TestRunner.java         # Cucumber test runner
├── src/test/resources/
│   ├── features/           # Cucumber feature files
│   ├── test.properties     # Test configuration
│   └── testdata.yaml       # Test data
├── config/
│   ├── dev.properties      # Dev environment config
│   ├── staging.properties  # Staging environment config
│   └── prod.properties     # Production environment config
└── .github/workflows/      # CI/CD pipelines
```

## 📦 Prerequisites

- **Java 21+** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **Git** ([Download](https://git-scm.com/))
- **Chrome Browser** (for local testing)
- **Python 3.8+** (for self-healing agent)

## 🚀 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/sulabh9595/SelfHealingBDDCucumber.git
cd SelfHealingBDDCucumber
```

### 2. Set JAVA_HOME and Maven Path
```bash
# macOS/Linux
export JAVA_HOME=/path/to/jdk-21
export M2_HOME=/path/to/apache-maven-3.9+

# Windows
set JAVA_HOME=C:\path\to\jdk-21
set M2_HOME=C:\path\to\apache-maven-3.9+
```

### 3. Verify Installation
```bash
java -version
mvn --version
```

## 🏃 Running Tests

### Run All Tests (Staging Environment)
```bash
mvn clean test
```

### Run Tests by Environment
```bash
# Development Environment
mvn clean test -Pdev

# Production Environment
mvn clean test -Pprod
```

### Run Tests by Browser
```bash
# Firefox
mvn clean test -Pfirefox

# Edge
mvn clean test -Pedge
```

### Run Specific Test Suite
```bash
mvn clean test -Dtest=AmazonTests
```

### Run in Headless Mode
```bash
mvn clean test -Dheadless=true
```

### Generate Allure Report
```bash
mvn clean test
mvn allure:serve
```

## ⚙️ Configuration

### Environment Configuration Files
Modify settings in `config/` directory:

- **dev.properties** - Development environment
- **staging.properties** - Staging environment  
- **prod.properties** - Production environment

### Test Properties
Update `src/test/resources/test.properties`:
```properties
app.url=https://www.amazon.in
browser=chrome
implicit.wait=15
explicit.wait=20
```

### Test Data
Update `src/test/resources/testdata.yaml`:
```yaml
amazon:
  searchLaptop: "laptop"
  credentials:
    username: "user@example.com"
    password: "password123"
```

## 📁 Project Structure

### Page Objects (`src/main/java/com/amazon/framework/pages/`)
- **BasePage.java** - Abstract base class with common methods
- **HomePage.java** - Home page object model
- **SearchResultsPage.java** - Search results page object
- **ProductPage.java** - Product details page object
- **CartPage.java** - Shopping cart page object

### Locators (`src/main/java/com/amazon/framework/locators/`)
- **HomePageLocators.java** - Home page element locators
- **SearchResultsPageLocators.java** - Search results locators
- **ProductPageLocators.java** - Product page locators
- **CartPageLocators.java** - Cart page locators

### Utilities (`src/main/java/com/amazon/framework/utils/`)
- **DriverManager.java** - WebDriver initialization & management
- **ConfigReader.java** - Configuration properties reader
- **YamlReader.java** - YAML test data reader

### Exceptions (`src/main/java/com/amazon/framework/exceptions/`)
- **FrameworkException.java** - Base exception class
- **ElementNotFoundException.java** - Element not found exception
- **TimeoutException.java** - Timeout exception
- **ConfigurationException.java** - Configuration error exception

## 🔧 Self-Healing Locators

The framework includes an AI agent that automatically detects and repairs failing locators:

```bash
python3 self_healing_agent.py \
  --mvn-path /path/to/mvn \
  --java-home /path/to/jdk
```

**Features:**
- Detects failed element locators from test output
- Applies predefined locator mappings
- Reruns tests automatically
- Generates healing reports

## 📊 Reporting

### Allure Reports
```bash
# Generate and serve Allure report
mvn allure:serve

# Report opens automatically at http://localhost:4040
```

### Code Coverage (JaCoCo)
```bash
mvn clean test
# Coverage report generated at: target/site/jacoco/index.html
```

### Code Quality (SonarQube)
```bash
mvn clean test sonar:sonar \
  -Dsonar.projectKey=SelfHealingBDDCucumber \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=your_token
```

## 🔄 CI/CD Pipeline

Automated tests run on:
- ✅ Push to `main` or `develop` branches
- ✅ Pull requests
- ✅ Daily schedule (2 AM UTC)

**Generated Artifacts:**
- Allure test reports
- JUnit XML results
- Code coverage reports

## 📝 Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines on:
- How to contribute
- Coding standards
- Creating new test cases
- Submitting pull requests

## 🐛 Troubleshooting

### Issue: WebDriver not found
```bash
# Ensure Chrome browser is installed and WebDriverManager handles it automatically
# If issues persist, manually set Chrome path:
mvn clean test -Dwebdriver.chrome.driver=/path/to/chromedriver
```

### Issue: Tests timeout
```bash
# Increase wait times in environment config
# Modify config/[environment].properties
explicit.wait=30
page.load.timeout=40
```

### Issue: Feature files not found
```bash
# Ensure feature files are in: src/test/resources/features/
# Check TestRunner.java has correct glue path
```

## 📚 Additional Resources

- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [TestNG Documentation](https://testng.org/doc/)
- [Allure Documentation](https://docs.qameta.io/allure/)

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👤 Author

**Sulabh** - [GitHub](https://github.com/sulabh9595)

## 🤝 Support

For issues and questions:
- Create an [Issue](https://github.com/sulabh9595/SelfHealingBDDCucumber/issues)
- Check existing documentation
- Review test examples

---

**Last Updated:** May 2026  
**Version:** 1.0.0
