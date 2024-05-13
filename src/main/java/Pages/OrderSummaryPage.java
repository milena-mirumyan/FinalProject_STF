package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSummaryPage extends BasePage {
    public OrderSummaryPage(WebDriver driver) {
        super(driver);
    }

    public ShoppingCartPage editCart(){
        click(By.cssSelector(Locators.editCart));
        return new ShoppingCartPage(driver);
    }
}
