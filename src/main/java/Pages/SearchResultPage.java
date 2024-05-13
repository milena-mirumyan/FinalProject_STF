package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
    private static final Logger LOGGER = Logger.getLogger(SearchResultPage.class.getName());
    public ProductViewPage openProduct(int index){
        driver.findElements(By.cssSelector(Locators.itemImage)).get(index).click();
        return new ProductViewPage(driver);
    }
    public boolean isSearchSuccessful(){
        return isElementPresent(By.cssSelector(Locators.searchSuccess));
    }
    public boolean isErrorDisplayed() {
        return isElementPresent(By.cssSelector(Locators.errorBookSearch));

    }

    public void sortByPriceHighToLow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the sorting options list to be visible and clickable
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Locators.sortingOptionsList)));
        Select select = new Select(dropdown);
        select.selectByValue("pricedesc");
    }
    public void sortByPriceLowToHigh() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Locators.sortingOptionsList)));
        Select select = new Select(dropdown);
        select.selectByValue("priceasc");
    }
    public List<Double> getSearchResultPrices() {
        List<WebElement> priceElements = driver.findElements(By.cssSelector(Locators.searchResultPrices));

        LOGGER.info("Number of price elements found: " + priceElements.size());

        return priceElements.stream()
                .map(WebElement::getText)
                .peek(text -> LOGGER.info("Raw price text: " + text))
                .map(text -> extractPrice(text))
                .peek(price -> LOGGER.info("Parsed price: " + price))
                .collect(Collectors.toList());
    }
    private Double extractPrice(String text) {
        // Split the text by space to handle cases with two prices
        String[] prices = text.split(" ");
        // Use the last price as the actual selling price
        String price = prices[prices.length - 1];
        // Remove the dollar sign and trim the text
        return Double.parseDouble(price.replace("$", "").trim());
    }

    public boolean isSortedLowToHigh(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            LOGGER.info("Comparing " + prices.get(i) + " <= " + prices.get(i + 1));
            if (prices.get(i) > prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSortedHighToLow(List<Double> prices) {
        for (int i = 0; i < prices.size() - 1; i++) {
            LOGGER.info("Comparing " + prices.get(i) + " >= " + prices.get(i + 1));
            if (prices.get(i) < prices.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
