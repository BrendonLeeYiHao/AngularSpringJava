package com.example.demo.seleniumtest.ui;

import static com.example.demo.seleniumtest.WebDriverUtils.*;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.example.demo.dto.UserDTO;
import com.example.demo.seleniumtest.LocatorType;
import com.example.demo.service.UserService;

@SpringBootTest
public class LoginPageTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test(description = "User enters correct name and password and click on login, thus login successfully")
    public void validateSuccessLogin() {

        WebElement navigateToLoginPage = findElement(byLocator(LocatorType.XPATH, "//button[text()='Login']"));
        clickOn(navigateToLoginPage);

        List<UserDTO> userList = userService.getAllUserDTO();
        for (UserDTO user: userList) {
            WebElement nameInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='name']"));
            sendKeys(nameInputField, user.getName());

            String enteredName = nameInputField.getAttribute("value");
            Assert.assertEquals(enteredName, user.getName(), "Name input field value is incorrect.");

            WebElement passwordInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='password']"));
            sendKeys(passwordInputField, user.getPassword());

            WebElement btnLogin = findElement(byLocator(LocatorType.XPATH, "//button/span[text()='Log in']"));
            clickOn(btnLogin);

            WebElement btnOk = findElement(byLocator(LocatorType.XPATH, "//h2[text()='Success']/following-sibling::div//button[text()='OK']"));
            clickOn(btnOk);
        }
    }

    @Test(description = "User enters incorrect name or password and click on login, error message is shown")
    public void validateFailedLogin() {

        WebElement navigateToLoginPage = findElement(byLocator(LocatorType.XPATH, "//button[text()='Login']"));
        clickOn(navigateToLoginPage);

        WebElement nameInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='name']"));
        sendKeys(nameInputField, "Test");

        WebElement passwordInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='password']"));
        sendKeys(passwordInputField, "test123");

        WebElement btnLogin = findElement(byLocator(LocatorType.XPATH, "//button/span[text()='Log in']"));
        clickOn(btnLogin);

        findElement(byLocator(LocatorType.XPATH, "//h2[text()='Error']/following-sibling::div//button[text()='OK']"));
    }

    @Test(description = "User enters nothing and click on login, error message is shown on respective fields")
    public void validateFailedLoginWithNoInput() {

        WebElement navigateToLoginPage = findElement(byLocator(LocatorType.XPATH, "//button[text()='Login']"));
        clickOn(navigateToLoginPage);

        WebElement btnLogin = findElement(byLocator(LocatorType.XPATH, "//button/span[text()='Log in']"));
        clickOn(btnLogin);

        findElement(byLocator(LocatorType.XPATH, "//div[text()='Name is required!']"));

        findElement(byLocator(LocatorType.XPATH, "//div[text()='Password is required!']"));
    }
}