package org.example;

import Pages.HomePage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BaseTest {
    protected static WebDriver driver;
    public static HomePage homePage;

    @BeforeMethod
    public void setUpClass() throws MalformedURLException {
//        FirefoxOptions options = new FirefoxOptions();
//        String gridHubUrl = "http://localhost:4444/wd/hub";
//        driver = new RemoteWebDriver(new URL(gridHubUrl), options);
//        driver.manage().window().maximize();
//        driver.get("https://www.k5learning.com/free-math-worksheets");
//        homePage = new HomePage(driver);
        System.setProperty("chromdriver.chrome.driver", "chromedriver-mac-arm64");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.k5learning.com/free-math-worksheets");
        homePage = new HomePage(driver);
    }

    @AfterClass
    public  void tearDownClass() {
//        if (driver != null) {
//            driver.quit();
//        }
    }
    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = "failure_" + timestamp + ".png";
            FileUtils.copyFile(screenshotFile, new File("screenshots/" + screenshotName));
        }
        driver.quit();
    }

//     @AfterMethod
//    public void recordFailure(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            var camera = (TakesScreenshot)driver;
//            File screenshot = camera.getScreenshotAs(OutputType.FILE);
//            try {
//                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
//            }
//            catch(IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
