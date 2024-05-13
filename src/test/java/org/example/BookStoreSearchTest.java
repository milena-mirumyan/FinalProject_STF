package org.example;
import Constants.AssertionMessages;
import Constants.Locators;
import Pages.BookStorePage;
import Pages.SearchResultPage;
//import org.testng.annotations.Parameters;
import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class BookStoreSearchTest extends BaseTest {

    /**
     * Test Case: testSearchValidBook
     * ID: TC001
     * Objective: To ensure that searching for a valid book title returns the correct search results.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "Decimals".
     * 3. Assert that the search results are displayed successfully.
     * Expected Result: The search should return results indicating the search was successful.
     */

    @Test
    @Parameters("searchText")
    public void testSearchValidBook(String searchText) {
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        SearchResultPage searchResultPage = bookStorePage.searchForBook(searchText);
        waitForPageLoad(Locators.searchSuccess);
        // Assert that the search was successful
        Assertions.assertTrue(searchResultPage.isSearchSuccessful(), AssertionMessages.searchUnsuccessful);
    }

    /**
     * Test Case: testSearchInvalidBook
     * ID: TC002
     * Objective: To ensure that searching for an invalid book title returns an appropriate error message.
     * Precondition: The user is on the K5 Learning website's home page.
     * Steps:
     * 1. Navigate to the Book Store page.
     * 2. Perform a search for the book title "hjbjhv".
     * 3. Wait for the search results page to load.
     * 4. Assert that an error message is displayed indicating no results were found.
     * Expected Result: The search should return an error message indicating that no results were found.
     */
    @Test
    @Parameters("searchText")
    public void testSearchInvalidBook(String searchText) {
        BookStorePage bookStorePage = homePage.navigateToBookStore();
        // Perform a search for an invalid book
        SearchResultPage searchResultPage = bookStorePage.searchForBook(searchText);
        waitForPageLoad(Locators.errorBookSearch);
        // Assert that an error message is displayed
        Assertions.assertTrue(searchResultPage.isErrorDisplayed(), AssertionMessages.errorIsNotDisplayed);
    }


    private void waitForPageLoad(String locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
    }
}
