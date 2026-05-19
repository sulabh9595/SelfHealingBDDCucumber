package com.amazon.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.amazon.framework.locators.SearchResultsPageLocators;

import java.util.List;
import java.util.Set;

public class SearchResultsPage extends BasePage {
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasResults() {
        try {
            find(SearchResultsPageLocators.SEARCH_RESULT_CARDS);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ProductPage openFirstResult() {
        String currentWindow = driver.getWindowHandle();
        try {
            click(SearchResultsPageLocators.FIRST_RESULT_PRIMARY);
        } catch (Exception firstError) {
            try {
                click(SearchResultsPageLocators.FIRST_RESULT_SECONDARY);
            } catch (Exception secondError) {
                click(SearchResultsPageLocators.FIRST_RESULT_FALLBACK);
            }
        }
        switchToNewWindow(currentWindow);
        return new ProductPage(driver);
    }

    private void switchToNewWindow(String originalWindow) {
        Set<String> windows = driver.getWindowHandles();
        if (windows.size() > 1) {
            for (String window : windows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }
        }
    }
}
