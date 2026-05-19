package com.amazon.framework.pages;

import org.openqa.selenium.WebDriver;
import com.amazon.framework.locators.CartPageLocators;

import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasItemsInCart() {
        List<?> items = driver.findElements(CartPageLocators.CART_ITEMS);
        return !items.isEmpty();
    }

    public String getCartCount() {
        return textOf(CartPageLocators.CART_COUNT);
    }

    public String getFirstItemName() {
        return textOf(CartPageLocators.CART_ITEM_NAMES);
    }
}
