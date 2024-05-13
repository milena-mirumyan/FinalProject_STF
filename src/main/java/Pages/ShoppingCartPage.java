package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public int getProductCount(){
        return driver.findElements(By.cssSelector(Locators.shoppingCartProducts)).size();
    }

    public String getProductName(int index){
        return driver.findElements(By.cssSelector(Locators.shoppingCartProductName)).get(index).getText();
    }

    public double getProductPrice(int index){
        WebElement element = driver.findElements(By.cssSelector(Locators.shoppingCartProductPrice)).get(index);
        return Double.parseDouble(element.getText().replace("$", "").trim());

    }


    public double getTotalUSD() {
        WebElement element = waitForElementVisible(By.cssSelector(Locators.totalPrice));
        return Double.parseDouble(element.getText().replace("$", "").trim());

    }
    public void changeQuantityOfItem1(String quantity) {
        waitForElementVisible(By.cssSelector(Locators.quantityDropdown));
        WebElement quantityDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(Locators.quantityDropdown)));
        Select select = new Select(quantityDropdown);
        select.selectByValue(quantity);

    }
    public boolean isNoProductsInCartVisible() {
        waitForElementInvisible(By.cssSelector(Locators.loading));
        return isElementPresent(By.cssSelector(Locators.noProductsInCart));
    }

}
