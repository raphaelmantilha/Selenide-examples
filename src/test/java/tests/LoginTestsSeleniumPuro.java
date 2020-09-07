package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class LoginTestsSeleniumPuro {
    public WebDriver browser;
    public WebDriverWait wait;
    public int timeout = 5;

    @BeforeTest
    public void startBrowser() {
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        wait = new WebDriverWait(browser, timeout);
        browser.get("http://ninjaplus-web:5000");
        Assert.assertEquals("Ninja+", browser.getTitle());
    }

    @AfterTest
    public void closeBrowser() {
        browser.close();
    }

    @Test
    public void shouldSeeLoggedUser() {
        browser.findElement(By.id("emailId")).sendKeys("papito@ninjaplus.com");
        browser.findElement(By.id("passId")).sendKeys("pwd123");
        browser.findElement(By.id("login")).click();
        String userInfo = ".user .info span";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(userInfo)));
        WebElement userName = browser.findElement(By.cssSelector(userInfo));
        Assert.assertEquals(userName.getText(),"Papito");
    }
}
