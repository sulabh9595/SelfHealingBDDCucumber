# Contributing to SelfHealingBDDCucumber

First off, thank you for considering contributing to our project! It's people like you that make this framework such a great tool.

## Code of Conduct

This project and everyone participating in it is governed by our Code of Conduct. By participating, you are expected to uphold this code.

## How Can I Contribute?

### Reporting Bugs 🐛

Before creating a bug report, please check the issue list as you might find out that you don't need to create one. When you are creating a bug report, please include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps which reproduce the problem**
- **Provide specific examples to demonstrate the steps**
- **Describe the behavior you observed after following the steps**
- **Explain which behavior you expected to see instead and why**
- **Include screenshots and/or animated GIFs if possible**
- **Include your environment details** (OS, Java version, Maven version, Browser)

### Suggesting Enhancements 💡

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion, please include:

- **Use a clear and descriptive title**
- **Provide a step-by-step description of the suggested enhancement**
- **Provide specific examples to demonstrate the steps**
- **Describe the current behavior and expected behavior**
- **Explain why this enhancement would be useful**

### Pull Requests 🚀

- Follow the Java and Cucumber style guides
- Include appropriate test cases
- Update documentation as needed
- End all files with a newline

## Development Setup

### 1. Fork and Clone
```bash
git clone https://github.com/YOUR_USERNAME/SelfHealingBDDCucumber.git
cd SelfHealingBDDCucumber
git remote add upstream https://github.com/sulabh9595/SelfHealingBDDCucumber.git
```

### 2. Create a Branch
```bash
git checkout -b feature/your-feature-name
# or
git checkout -b bugfix/your-bug-name
```

### 3. Make Changes
Follow the coding standards below.

### 4. Test Your Changes
```bash
mvn clean test
```

### 5. Commit and Push
```bash
git commit -m "Descriptive commit message"
git push origin feature/your-feature-name
```

### 6. Create a Pull Request
- Provide a clear description of your changes
- Reference any related issues
- Ensure CI/CD pipeline passes

## Coding Standards

### Java Code Style

- **Indentation**: 4 spaces (no tabs)
- **Line Length**: Max 120 characters
- **Naming Conventions**:
  - Classes: PascalCase (e.g., `HomePage`)
  - Methods: camelCase (e.g., `searchProduct()`)
  - Constants: UPPER_SNAKE_CASE (e.g., `MAX_WAIT_TIME`)
  - Variables: camelCase (e.g., `searchQuery`)

### Example:
```java
public class HomePage extends BasePage {
    private static final int MAX_WAIT_TIME = 15;
    private String productName;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        this.productName = productName;
        type(HomePageLocators.SEARCH_BOX, productName);
        click(HomePageLocators.SEARCH_BUTTON);
    }
}
```

### Comments and Documentation

- Add JavaDoc to all public methods
- Use meaningful variable names (avoid single letters except for loop counters)
- Comment complex logic

```java
/**
 * Searches for a product on Amazon.
 * 
 * @param productName the name of the product to search
 * @throws ElementNotFoundException if search elements are not found
 */
public void searchProduct(String productName) {
    // Implementation
}
```

### Cucumber Feature Files

- Use clear, business-readable scenarios
- Follow Gherkin syntax properly
- Group related scenarios

```gherkin
Feature: Amazon Product Search
  As a user
  I want to search for products
  So that I can find items to purchase

  Scenario: Search for laptop
    Given user is on Amazon home page
    When user searches for "laptop"
    Then search results are displayed
    And results contain laptop products
```

## Testing Requirements

All contributions must include tests:

- ✅ Unit tests for utility classes
- ✅ Step definitions for new features
- ✅ Page object tests if adding new pages
- ✅ Integration tests for complete workflows

### Running Tests
```bash
# Run all tests
mvn clean test

# Run specific test class
mvn test -Dtest=StepDefinitionsTest

# Run with coverage
mvn clean test jacoco:report
```

## Commit Messages

Write clear, concise commit messages:

```
[FEATURE] Add multi-browser support

- Add Firefox and Edge driver support
- Update DriverManager with browser factory
- Add browser-specific configurations
- Closes #123
```

**Commit Types:**
- `[FEATURE]` - New functionality
- `[BUGFIX]` - Bug fixes
- `[REFACTOR]` - Code refactoring
- `[DOCS]` - Documentation updates
- `[TEST]` - Test additions or modifications
- `[CHORE]` - Build, dependencies, tooling

## Documentation

- Update README.md if you change functionality
- Add comments to complex code
- Update this CONTRIBUTING.md if you change development processes
- Create/update technical documentation in `docs/` if applicable

## Branch Naming

```
feature/short-description      # New features
bugfix/short-description       # Bug fixes
refactor/short-description     # Refactoring
docs/short-description         # Documentation
```

## Review Process

1. Automated checks must pass (CI/CD)
2. Code review by maintainers
3. Approval required before merging
4. Squash and merge to main branch

## Questions?

Feel free to:
- Open an issue with the label `question`
- Check existing discussions
- Contact the maintainers

---

**Thank you for contributing! 🎉**
