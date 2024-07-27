package com.example.demo.seleniumtest.ui;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.example.demo.seleniumtest.LocatorType;

import static com.example.demo.seleniumtest.WebDriverUtils.*;

public class HomePageTest extends BaseTest {

	@Test
	public void validateSuccessRegistration() {

		WebElement nameInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='name']"));
		sendKeys(nameInputField, "Cecelia");
		
		WebElement passwordInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='password']"));
		sendKeys(passwordInputField, "abc123");
		
		WebElement emailInputField = findElement(byLocator(LocatorType.XPATH, "//input[@formControlName='email']"));
		sendKeys(emailInputField, "cecelia1@gmail.com");
		
		WebElement datePicker = findElement(byLocator(LocatorType.XPATH, "//label[text()='Date of Birth']/parent::nz-form-label/following-sibling::nz-form-control/div/div/nz-date-picker[@formControlName='dob']"));
		clickOn(datePicker);
		
		WebElement dateSelected = findElement(byLocator(LocatorType.XPATH, "//div[@class='ant-picker-date-panel']/div/date-table/table/tbody//td[1]"));
		clickOn(dateSelected);
		
		WebElement genderDropDown = findElement(byLocator(LocatorType.XPATH, "//label[text()='Gender']/parent::nz-form-label/following-sibling::nz-form-control/div/div/nz-select"));
		clickOn(genderDropDown);
		
		WebElement maleOption = findElement(byLocator(LocatorType.XPATH, "//nz-option-container/div//div/nz-option-item/div[text()='Male']"));
		clickOn(maleOption);
		
		WebElement btnSubmit = findElement(byLocator(LocatorType.XPATH, "//button//span[contains(text(), 'Register')]"));
		clickOn(btnSubmit);
		
		WebElement btnOk = findElement(byLocator(LocatorType.XPATH, "//button[text()='OK']"));
		clickOn(btnOk);
	}
}