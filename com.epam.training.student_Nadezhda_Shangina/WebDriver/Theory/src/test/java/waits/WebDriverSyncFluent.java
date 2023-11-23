package waits;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WebDriverSyncFluent {
    public static void main(String[] args)  {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/");
        //risky point here

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(CustomConditions.jQueryAJAXsCompleted());

//        using own method
        WebElement searchClass = waitForElementLocateBy(driver, By.className("DocSearch-Search-Icon"));
        searchClass.click();
        WebElement searchInput = driver.findElement(By.className("DocSearch-Input"));
        searchInput.sendKeys("selenium java");
        //risky point here

        Wait<WebDriver> wait =new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchFieldException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting search result list was exceeded!");

        List<WebElement> searchResults=wait.until(new Function<WebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(By
                        .xpath("//div[contains(@class, 'DocSearch-Hit-Container') and contains(., 'Selenium') and contains(., 'Java') ]"));
            }
        });

//        List<WebElement> searchResult=waitForElementsLocateBy(driver,
//                By.xpath("//div[contains(@class, 'DocSearch-Hit-Container') and contains(., 'Selenium') and contains(., 'Java') ]"));
        driver.quit();
        System.out.println("number of results = "+searchResults.size());
    }

    //      search with expectation
    private static WebElement waitForElementLocateBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
