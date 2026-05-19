package com.amazon.framework.locators;

import org.openqa.selenium.By;

public class SearchResultsPageLocators {
    public static final By FIRST_RESULT_PRIMARY = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a, div.s-main-slot .s-result-item h2 a");
    public static final By FIRST_RESULT_SECONDARY = By.cssSelector("div[data-component-type='s-search-result'] h2 a, div[data-component-type='s-search-result'] .a-link-normal, div.s-main-slot div.s-result-item h2 a");
    public static final By FIRST_RESULT_FALLBACK = By.xpath("//div[contains(@class,'s-main-slot')]//h2//a");
    public static final By SEARCH_RESULT_CARDS = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'], div[data-component-type='s-search-result'], div.s-main-slot .s-result-item, div.s-main-slot div.s-result-item");
}
