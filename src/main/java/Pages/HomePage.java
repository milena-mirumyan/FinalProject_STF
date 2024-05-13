package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public BookStorePage navigateToBookStore() {
        click(By.cssSelector(Locators.bookStore));
        return new BookStorePage(this.driver);
    }

}
