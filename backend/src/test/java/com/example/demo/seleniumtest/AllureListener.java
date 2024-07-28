package com.example.demo.seleniumtest;

import static com.example.demo.seleniumtest.WebDriverUtils.getDriver;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Allure;

public class AllureListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        if (getDriver() instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(result.getMethod().getMethodName()+ "_success.png", "image/png", "png", screenshot);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (getDriver() instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.getLifecycle().addAttachment(result.getMethod().getMethodName()+ "_failure.png", "image/png", "png", screenshot);
        }
    }
}