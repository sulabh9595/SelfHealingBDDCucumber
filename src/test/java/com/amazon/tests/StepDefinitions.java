package com.amazon.tests;

import com.amazon.framework.pages.CartPage;
import com.amazon.framework.pages.HomePage;
import com.amazon.framework.pages.ProductPage;
import com.amazon.framework.pages.SearchResultsPage;
import com.amazon.framework.utils.ConfigReader;
import com.amazon.framework.utils.DriverManager;
import com.amazon.framework.utils.YamlReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Map;

public class StepDefinitions {
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private Map<String, Object> amazonData;
    private String selectedSearchTerm;

    @Given("the user selects test data {string}")
    public void the_user_selects_test_data(String key) {
        amazonData = YamlReader.getSection("amazon");
        Assert.assertFalse(amazonData.isEmpty(), "Amazon section in testdata.yaml must not be empty");
        Assert.assertTrue(amazonData.containsKey(key), "Test data key not found: " + key);
        selectedSearchTerm = amazonData.get(key).toString();
    }

    @Given("the user opens the Amazon home page")
    public void the_user_opens_the_amazon_home_page() {
        var driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("app.url"));
        homePage = new HomePage(driver);
    }

    @When("the user searches for the mapped item")
    public void the_user_searches_for_the_mapped_item() {
        Assert.assertNotNull(selectedSearchTerm, "Selected test data item must be initialized");
        searchResultsPage = homePage.searchFor(selectedSearchTerm);
    }

    @Then("results should appear")
    public void results_should_appear() {
        Assert.assertTrue(searchResultsPage.hasResults(), "Expected search results to appear");
    }

    @When("the user opens the first search result")
    public void the_user_opens_the_first_search_result() {
        productPage = searchResultsPage.openFirstResult();
    }

    @Then("the product details page should load")
    public void the_product_details_page_should_load() {
        Assert.assertFalse(productPage.getProductTitle().isEmpty(), "Expected product title to be visible");
    }

    @When("the user adds the product to cart")
    public void the_user_adds_the_product_to_cart() {
        productPage.addToCart();
    }

    @When("the user opens the cart page")
    public void the_user_opens_the_cart_page() {
        cartPage = productPage.openCart();
    }

    @Then("the cart should contain items")
    public void the_cart_should_contain_items() {
        Assert.assertTrue(cartPage.hasItemsInCart(), "Expected the cart to contain at least one item");
    }
}
