package com.example.demo.seleniumtest.ui;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.example.demo.seleniumtest.Constants;

import static com.example.demo.seleniumtest.WebDriverUtils.*;

public class BaseTest extends AbstractTestNGSpringContextTests {

    @BeforeMethod
    public void setUp() {
        getDriver();
        setWindowSize();
        getUrl(Constants.URL);
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}