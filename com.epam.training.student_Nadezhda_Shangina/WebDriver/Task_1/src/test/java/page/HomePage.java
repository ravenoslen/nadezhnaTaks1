package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private static final String HOMEPAGE_URL="https://www.pastebin.com";
    private WebDriver webDriver;
    private final int WAIT_TIMEOUT_SECOND = 10;

    private WebElement textareaCode;
    private final By textareaCodeLocator=By.id("postform-text");

    private Select selectionExpiration;
    private final By selectionExpirationLocator=By.id("select2-postform-expiration-container");



    public HomePage(WebDriver webDriver) {
        this.webDriver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public HomePage openPage () {
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECOND));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(WAIT_TIMEOUT_SECOND));
        webDriver.get(HOMEPAGE_URL);
        return this;
    }

    public HomePage setCode (String code) {
        textareaCode=waitForElementLocateBy(webDriver, textareaCodeLocator);
        textareaCode.click();
        textareaCode.sendKeys(code);
        return this;
    }

    public HomePage setExpiration (String expiration) {
//        waitForElementLocateBy(webDriver, selectionExpirationLocator);
        selectionExpiration=new Select(waitForElementLocateBy(webDriver, selectionExpirationLocator));

        return this;
    }



    private static WebElement waitForElementLocateBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
