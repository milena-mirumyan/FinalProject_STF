package org.example;

import Constants.AssertionMessages;
import Constants.Locators;
import Pages.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;


public class ChangeItemQuantityTest extends BaseTest {

    /**
     * Test Case: testChangeItemQuantity
     * ID: TC007
     * Objective: To ensure that the quantity of an item in the cart can be successfully changed.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "Decimals".
     * 3. Open the first product from the search results.
     * 4. Retrieve the item's name and price.
     * 5. Add the item to the cart.
     * 6. Proceed to checkout.
     * 7. Edit the cart to verify the item details.
     * 8. Assert that the item count, name, price, and total price in the cart are correct.
     * 9. Change the quantity of the first item in the cart to a new quantity.
     * 10. Assert that the item has been deleted from the cart successfully.
     * Expected Result: The item should not be visible in the shopping cart anymore.
     */
    @Test
    public void testChangeItemQuantity() {
        // Navigate to the bookstore and add an item to the cart
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        SearchResultPage searchResultPage = bookStorePage.searchForBook("Decimals");
        ProductViewPage productViewPage = searchResultPage.openProduct(0);
        double itemPrice1 = productViewPage.getItemPrice();
        String itemName = productViewPage.getItemName();
        ProceedToCheckoutModal proceedToCheckoutModal = productViewPage.addToCart();
        waitForPageLoad();
        OrderSummaryPage orderSummaryPage = proceedToCheckoutModal.proceedToCheckout();
        ShoppingCartPage shoppingCartPage = orderSummaryPage.editCart();
        Assertions.assertEquals(1, shoppingCartPage.getProductCount());
        Assertions.assertEquals(itemName, shoppingCartPage.getProductName(0));
        Assertions.assertEquals(itemPrice1, shoppingCartPage.getProductPrice(0));
        Assertions.assertEquals(itemPrice1, shoppingCartPage.getTotalUSD());
        // Change the quantity of the first item in the cart
        String newQuantity = "3";
        shoppingCartPage.changeQuantityOfItem1(newQuantity);
        // Verify that the item is gone from the shoppingList
        Assertions.assertTrue(shoppingCartPage.isNoProductsInCartVisible(), AssertionMessages.itemIsVisible);

    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(Locators.loading))));
    }

}
