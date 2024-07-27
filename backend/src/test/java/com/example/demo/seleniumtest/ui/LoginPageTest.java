package com.example.demo.seleniumtest.ui;

import static com.example.demo.seleniumtest.WebDriverUtils.byLocator;
import static com.example.demo.seleniumtest.WebDriverUtils.clickOn;
import static com.example.demo.seleniumtest.WebDriverUtils.findElement;
import static com.example.demo.seleniumtest.WebDriverUtils.sendKeys;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.demo.seleniumtest.LocatorType;

public class LoginPageTest extends BaseTest {

    @Test
    public void validateSuccessLogin() {

        WebElement navigateToLoginPage = findElement(byLocator(LocatorType.XPATH, "//button/span[text()='Login']"));
        clickOn(navigateToLoginPage);

        WebElement nameInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='name']"));
		sendKeys(nameInputField, "Willon");

        WebElement passwordInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='password']"));
		sendKeys(passwordInputField, "abc123");

        WebElement btnLogin = findElement(byLocator(LocatorType.XPATH, "//button/span[text()='Log in']"));
        clickOn(btnLogin);

        WebElement btnOk = findElement(byLocator(LocatorType.XPATH, "//h2[text()='Success']/following-sibling::div//button[text()='OK']"));
		clickOn(btnOk);
    }
}