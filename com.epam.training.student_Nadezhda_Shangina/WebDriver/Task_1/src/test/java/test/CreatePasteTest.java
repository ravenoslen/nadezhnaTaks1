package test;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page.HomePage;

public class CreatePasteTest {

    HomePage homePage;
    ChromeDriver driver;

    @BeforeTest
    public void startTest() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);

    }

    @AfterTest
    public void endTest() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    @Test
    public void CreatePasteCodeExpirationName() {
        homePage = new HomePage(driver);
        homePage.openPage();
        homePage.setCode("Hello from WebDriver");
//        homePage.setExpiration("10 Minutes");

    }


}
