package com.example.demo.seleniumtest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
	
    private static WebDriver driver;
    private static WebDriverWait wait;
    
    // Method to initialize WebDriver
    public static void initializeDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
    }

    // Method to get WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    public static void setWindowSize() {
        driver.manage().window().setSize(new Dimension(1366, 768));
    }
    
    public static void getUrl(String url) {
    	driver.get(url);
    }

    // Method to quit WebDriver
    public static void quitDriver() {
		try {
			Thread.sleep(5000);
	        if (driver != null) {
	            driver.quit();
	            driver = null;
	        }
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public static WebElement findElement(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
	
    public static void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public static void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    
    public static By byLocator(LocatorType type, String value) {
    	switch (type) {
    		case XPATH:
    			return By.xpath(value);
			case CLASS_NAME:
				return By.className(value);
			case CSS_SELECTOR:
				return By.cssSelector(value);
			case ID:
				return By.id(value);
			default:
				throw new IllegalArgumentException("Unsupported Locator Type: " + type);
    	}
    }
}