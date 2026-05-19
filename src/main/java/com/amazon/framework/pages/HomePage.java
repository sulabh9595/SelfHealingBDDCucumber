package com.amazon.framework.pages;

import org.openqa.selenium.WebDriver;
import com.amazon.framework.locators.HomePageLocators;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openUrl(String url) {
        driver.get(url);
        return this;
    }

    public SearchResultsPage searchFor(String query) {
        type(HomePageLocators.SEARCH_BOX, query);
        click(HomePageLocators.SEARCH_BUTTON);
        return new SearchResultsPage(driver);
    }
}
