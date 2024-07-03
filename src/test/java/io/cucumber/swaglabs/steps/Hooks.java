package io.cucumber.swaglabs.steps;

import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.swaglabs.utils.DriverManager;
import io.cucumber.swaglabs.utils.ScreenshotUtil;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Hooks {
    public static WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            // Get current timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            // Create the screenshot name with the timestamp
            String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + timestamp + ".png";
            // Generate the file path
            String filePath = Paths.get(System.getProperty("user.dir"), "screenshots", screenshotName).toString();
            // Take the screenshot
            ScreenshotUtil.takeScreenshot(driver, filePath);
        }
        DriverManager.closeDriver();
    }
}
