package com.amazon.tests;

import com.amazon.framework.utils.DriverManager;
import com.amazon.framework.utils.YamlReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;

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
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
