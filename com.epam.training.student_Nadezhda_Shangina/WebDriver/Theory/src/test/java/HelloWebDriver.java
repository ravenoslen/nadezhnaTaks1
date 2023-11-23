import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import waits.CustomConditions;

import java.time.Duration;
import java.util.List;

public class HelloWebDriver {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

//        Deprecated. Too general
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.selenium.dev/");
        //risky point here

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(CustomConditions.jQueryAJAXsCompleted());

//        using own method
        WebElement searchClass = waitForElementLocateBy(driver, By.className("DocSearch-Search-Icon"));
        searchClass.click();
        WebElement searchInput = driver.findElement(By.className("DocSearch-Input"));
        searchInput.sendKeys("selenium java");
        //risky point here

        List<WebElement> searchResult=waitForElementsLocateBy(driver,
                By.xpath("//div[contains(@class, 'DocSearch-Hit-Container') and contains(., 'Selenium') and contains(., 'Java') ]"));
        driver.quit();
        System.out.println("number of results = "+searchResult.size());

//         WebElement searchBtn=driver.findElement(By.xpath(""));
//         searchBtn.click();

//        don't solve Synchronization problems
//        Thread.sleep(2000);
    }

    //      search with expectation
    private static WebElement waitForElementLocateBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }
    private static List<WebElement> waitForElementsLocateBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

}
