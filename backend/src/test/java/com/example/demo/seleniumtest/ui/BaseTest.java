package com.example.demo.seleniumtest.ui;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.example.demo.seleniumtest.Constants;

import static com.example.demo.seleniumtest.WebDriverUtils.*;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        getDriver();
        getUrl(Constants.URL);
    }

    @AfterClass
    public void tearDown() {
        quitDriver();
    }
}