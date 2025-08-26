package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

    // ----------------- DataProvider для позитивных тестов -----------------
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentials() {
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!"}
                // It is possible to add some more users to check boundary values
        };
    }

    @Test(dataProvider = "validCredentials")
    public void testSuccessfulLogin(String usernameValue, String passwordValue) {
        driver.get("https://the-internet.herokuapp.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys(usernameValue);
        passwordInput.sendKeys(passwordValue);
        loginButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"));
    }

    // ----------------- DataProvider для негативных тестов -----------------
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][] {
                {"wronguser", "wrongpassword", "Your username is invalid!"},
                {"tomsmith", "wrongpassword", "Your password is invalid!"},
                {"", "", "Your username is invalid!"},
                {"", "SuperSecretPassword!", "Your username is invalid!"},
                {"tomsmith", "", "Your password is invalid!"}
        };
    }

    @Test(dataProvider = "invalidCredentials")
    public void testInvalidLogin(String usernameValue, String passwordValue, String expectedMessage) {
        driver.get("https://the-internet.herokuapp.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys(usernameValue);
        passwordInput.sendKeys(passwordValue);
        loginButton.click();

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        Assert.assertTrue(errorMessage.getText().contains(expectedMessage));
    }
}
