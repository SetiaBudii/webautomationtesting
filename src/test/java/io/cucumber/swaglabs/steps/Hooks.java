package io.cucumber.swaglabs.steps;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.swaglabs.utils.DriverManager;
import io.cucumber.swaglabs.utils.ScreenshotUtil;
import java.nio.file.Paths;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replaceAll(" ", "_") + ".png";
            String filePath = Paths.get(System.getProperty("user.dir"), "screenshots", screenshotName).toString();
            ScreenshotUtil.takeScreenshot(driver, filePath);
        }
        DriverManager.closeDriver();
    }
}
