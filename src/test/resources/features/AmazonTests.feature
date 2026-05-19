Feature: Amazon search and cart workflows
  Validate Amazon search, product details, and cart behavior using BDD Cucumber.

  Scenario: Search for a laptop and verify results
    Given the user selects test data "searchLaptop"
    And the user opens the Amazon home page
    When the user searches for the mapped item
    Then results should appear

  Scenario: Search for a wireless mouse and verify results
    Given the user selects test data "searchMouse"
    And the user opens the Amazon home page
    When the user searches for the mapped item
    Then results should appear

  Scenario: Open the first search result and verify product details
    Given the user selects test data "searchHeadphones"
    And the user opens the Amazon home page
    When the user searches for the mapped item
    And the user opens the first search result
    Then the product details page should load

  Scenario: Add the first search result to cart and validate the cart
    Given the user selects test data "searchBook"
    And the user opens the Amazon home page
    When the user searches for the mapped item
    And the user opens the first search result
    And the user adds the product to cart
    And the user opens the cart page
    Then the cart should contain items

  Scenario: Search for gift card products and verify results
    Given the user selects test data "searchGift"
    And the user opens the Amazon home page
    When the user searches for the mapped item
    Then results should appear
