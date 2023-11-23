package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import waits.CustomConditions;

import java.time.Duration;
import java.util.List;

public class HelloWebDriverTest {
//    public static void main(String[] args) throws InterruptedException {
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.selenium.dev/");
//        new WebDriverWait(driver, Duration.ofSeconds(10)).until(CustomConditions.jQueryAJAXsCompleted());
//        WebElement searchClass = waitForElementLocateBy(driver, By.className("DocSearch-Search-Icon"));
//        searchClass.click();
//        WebElement searchInput = driver.findElement(By.className("DocSearch-Input"));
//        searchInput.sendKeys("selenium java");
//
//        List<WebElement> searchResult=waitForElementsLocateBy(driver,
//                By.xpath("//div[contains(@class, 'DocSearch-Hit-Container') and contains(., 'Selenium') and contains(., 'Java') ]"));
//        driver.quit();
//        System.out.println("number of results = "+searchResult.size());
//    }
//
//    private static WebElement waitForElementLocateBy(WebDriver driver, By by) {
//        return new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//    private static List<WebElement> waitForElementsLocateBy(WebDriver driver, By by) {
//        return new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
//    }

    @Test (description = "Just first test")
    public void commonSearchTermResultsAreNotEmpty() {
//        WebDriver driver=new ChromeDriver();
//        HomePage home=new HomePage(driver);
        int expectedSearchResultNumber= new HomePage(new ChromeDriver())
                .openPage()
                .searchForTerms("selenium java")
                .countSearchResult();
        Assert.assertTrue(expectedSearchResultNumber>0, "Search result is empty");

    }


}
