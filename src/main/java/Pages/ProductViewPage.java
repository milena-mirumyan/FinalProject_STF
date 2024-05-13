package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductViewPage extends BasePage {
    public ProductViewPage(WebDriver driver) {
        super(driver);
    }


    public ProceedToCheckoutModal addToCart() {
        click(By.cssSelector(Locators.addToCartButton));
        //waitForElementInvisible(By.cssSelector(Locators.loading));
        return new ProceedToCheckoutModal(driver);
    }

    public double getItemPrice() {
        WebElement element = waitForElementVisible(By.cssSelector(Locators.itemPrice));
        System.out.println(element.getText());
        return Double.parseDouble(element.getText().replace("$", "").trim());
    }

    public String getItemName() {
        WebElement element = waitForElementVisible(By.cssSelector(Locators.itemName));
        return element.getText();
    }

}
