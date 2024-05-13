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


public class AddToCartTest extends BaseTest {

    /**
     * Test Case: testAddToCart
     * ID: TC005
     * Objective: To ensure that a single item can be successfully added to the cart.
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
     * Expected Result: The item should be successfully added to the cart with correct details.
     */
    @Test
    public void testAddToCart() {
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
        Assertions.assertEquals(itemPrice1, shoppingCartPage.getTotalUSD(), AssertionMessages.totalPriceError);
    }

    /**
     * Test Case: testAddMultipleItemsToCart
     * ID: TC006
     * Objective: To ensure that multiple items can be successfully added to the cart.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "Decimals".
     * 3. Open the first product from the search results.
     * 4. Retrieve the first item's name and price.
     * 5. Add the first item to the cart.
     * 6. Navigate back to the search results.
     * 7. Open the second product from the search results.
     * 8. Retrieve the second item's name and price.
     * 9. Add the second item to the cart.
     * 10. Proceed to checkout.
     * 11. Edit the cart to verify the items' details.
     * 12. Assert that the item count, names, prices, and total price in the cart are correct.
     * Expected Result: Both items should be successfully added to the cart with correct details.
     */
    @Test
    public void testAddMultipleItemsToCart() {
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        SearchResultPage searchResultPage = bookStorePage.searchForBook("Decimals");
        ProductViewPage productViewPage = searchResultPage.openProduct(0);
        double itemPrice1 = productViewPage.getItemPrice();
        String itemName1 = productViewPage.getItemName();
        ProceedToCheckoutModal proceedToCheckoutModal = productViewPage.addToCart();
        driver.navigate().back();
        ProductViewPage productViewPage2 = searchResultPage.openProduct(1);
        double itemPrice2 = productViewPage2.getItemPrice();
        String itemName2 = productViewPage.getItemName();
        ProceedToCheckoutModal proceedToCheckoutModal2 = productViewPage.addToCart();
        waitForPageLoad();
        OrderSummaryPage orderSummaryPage = proceedToCheckoutModal2.proceedToCheckout();
        ShoppingCartPage shoppingCartPage = orderSummaryPage.editCart();
        Assertions.assertEquals(2, shoppingCartPage.getProductCount());
        Assertions.assertEquals(itemName1, shoppingCartPage.getProductName(0));
        Assertions.assertEquals(itemName2, shoppingCartPage.getProductName(1));
        Assertions.assertEquals(itemPrice1, shoppingCartPage.getProductPrice(0));
        Assertions.assertEquals(itemPrice2, shoppingCartPage.getProductPrice(1));
        // Verify the total price
        double totalUSD = shoppingCartPage.getTotalUSD();
        Assertions.assertEquals(itemPrice1 + itemPrice2, totalUSD, 0.01, AssertionMessages.totalPriceError);
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(Locators.loading))));
    }
}
