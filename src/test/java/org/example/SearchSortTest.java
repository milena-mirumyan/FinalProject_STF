package org.example;

import Constants.AssertionMessages;
import Constants.Locators;
import Pages.BookStorePage;
import Pages.SearchResultPage;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchSortTest extends BaseTest {


    /**
     * Test Case: testSortByPriceLowToHigh
     * ID: TC003
     * Objective: To ensure that sorting search results by price from low to high works correctly.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "Decimals".
     * 3. Sort the search results by price from low to high.
     * 4. Retrieve the list of prices from the search results.
     * 5. Assert that the prices are sorted from low to high.
     * Expected Result: The search results should be sorted by price from low to high.
     */
    @Test
    public void testSortByPriceLowToHigh() {
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        SearchResultPage searchResultPage = bookStorePage.searchForBook("Decimals");
        searchResultPage.sortByPriceLowToHigh();
        waitForPageLoad();

        List<Double> prices = searchResultPage.getSearchResultPrices();
        Assertions.assertTrue(searchResultPage.isSortedLowToHigh(prices), AssertionMessages.lowToHighSortError);
    }

    /**
     * Test Case: testSortByPriceHighToLow
     * ID: TC004
     * Objective: To ensure that sorting search results by price from high to low works correctly.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "Decimals".
     * 3. Sort the search results by price from high to low.
     * 4. Retrieve the list of prices from the search results.
     * 5. Assert that the prices are sorted from high to low.
     * Expected Result: The search results should be sorted by price from high to low.
     */
    @Test
    public void testSortByPriceHighToLow() {
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        SearchResultPage searchResultPage = bookStorePage.searchForBook("Decimals");
        searchResultPage.sortByPriceHighToLow();
        waitForPageLoad();

        List<Double> prices = searchResultPage.getSearchResultPrices();
        Assertions.assertTrue(searchResultPage.isSortedHighToLow(prices), AssertionMessages.highToLowSortError);
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(Locators.loading))));
    }
}
