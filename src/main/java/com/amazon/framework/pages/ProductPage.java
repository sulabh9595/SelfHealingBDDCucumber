package com.amazon.framework.pages;

import org.openqa.selenium.WebDriver;
import com.amazon.framework.locators.ProductPageLocators;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductTitle() {
        return textOf(ProductPageLocators.PRODUCT_TITLE);
    }

    public ProductPage addToCart() {
        click(ProductPageLocators.ADD_TO_CART_BUTTON);
        return this;
    }

    public CartPage openCart() {
        click(ProductPageLocators.NAV_CART_BUTTON);
        return new CartPage(driver);
    }
}
