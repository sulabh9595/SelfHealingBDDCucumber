package com.amazon.framework.locators;

import org.openqa.selenium.By;

public class ProductPageLocators {
    public static final By ADD_TO_CART_BUTTON = By.cssSelector("#add-to-cart-button, #submit.add-to-cart, [name='submit.add-to-cart'], input#add-to-cart-button");
    public static final By PRODUCT_TITLE = By.cssSelector("#productTitle, span#productTitle, #title #productTitle");
    public static final By NAV_CART_BUTTON = By.cssSelector("#nav-cart, #nav-cart-count");
}
