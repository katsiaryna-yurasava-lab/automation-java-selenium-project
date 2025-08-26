package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FirstTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @Test
    public void openGoogleAndSearch() {
        driver.get("https://www.google.com/ncr");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Закрываем consent, если есть
        try {
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.id("L2AGLb")));
            acceptCookies.click();
        } catch (Exception e) {
            // если баннера нет — продолжаем
        }

        // Ждём, пока поле поиска станет кликабельным
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys("Selenium TestNG");
        searchBox.submit();

        // Ждём, пока заголовок обновится
        wait.until(ExpectedConditions.titleContains("Selenium TestNG"));

        Assert.assertTrue(driver.getTitle().contains("Selenium TestNG"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
