package Pages;

import Constants.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProceedToCheckoutModal extends BasePage {

    public ProceedToCheckoutModal(WebDriver driver) {
        super(driver);
    }

    public OrderSummaryPage proceedToCheckout() {
        waitForPageLoad();
        click(By.cssSelector(Locators.proceedToCheckout));
        return new OrderSummaryPage(driver);
    }

    private void waitForPageLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(Locators.proceedToCheckout))));
    }
}
