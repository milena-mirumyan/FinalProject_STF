package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookStorePage extends BasePage {

    public BookStorePage(WebDriver driver) {
        super(driver);
    }


    public SearchResultPage searchForBook(String bookName) {
        type(By.cssSelector(Locators.bookStoreSearch), bookName);
        driver.findElement(By.cssSelector(Locators.bookStoreSearch)).sendKeys(Keys.ENTER);
        return new SearchResultPage(driver);
    }


    protected void click(By locator) {
        WebElement element = driver.findElement(locator);
        element.click();
    }
    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

}
