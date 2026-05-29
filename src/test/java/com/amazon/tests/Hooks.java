package com.amazon.tests;

import com.amazon.framework.utils.DriverManager;
import com.amazon.framework.utils.YamlReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {
    private static boolean yamlValidated = false;

    @Before(order = 0)
    public void validateYaml() {
        if (!yamlValidated) {
            YamlReader.validateTestDataYaml();
            yamlValidated = true;
        }
    }

    @Before(order = 1)
    public void setUp() {
        DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                if (DriverManager.isDriverActive()) {
                    byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", scenario.getName());
                }
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
            }
        }
        DriverManager.quitDriver();
    }
}
