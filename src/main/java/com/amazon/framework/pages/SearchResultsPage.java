package com.amazon.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.amazon.framework.locators.SearchResultsPageLocators;

import java.util.List;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasResults() {
        List<WebElement> results = driver.findElements(SearchResultsPageLocators.SEARCH_RESULT_CARDS);
        return !results.isEmpty();
    }

    public ProductPage openFirstResult() {
        try {
            click(SearchResultsPageLocators.FIRST_RESULT_PRIMARY);
        } catch (Exception firstError) {
            try {
                click(SearchResultsPageLocators.FIRST_RESULT_SECONDARY);
            } catch (Exception secondError) {
                click(SearchResultsPageLocators.FIRST_RESULT_FALLBACK);
            }
        }
        return new ProductPage(driver);
    }
}
