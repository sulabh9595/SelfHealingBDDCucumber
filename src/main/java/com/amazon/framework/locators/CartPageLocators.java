package com.amazon.framework.locators;

import org.openqa.selenium.By;

public class CartPageLocators {
    public static final By CART_ITEMS = By.cssSelector("div.sc-list-item");
    public static final By CART_ITEM_NAMES = By.cssSelector("span.a-truncate-cut");
    public static final By CART_COUNT = By.id("nav-cart-count");
}
