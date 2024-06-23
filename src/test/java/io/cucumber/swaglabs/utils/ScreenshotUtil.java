package io.cucumber.swaglabs.utils;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String filePath) {
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        File screenshotFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        Path path = Paths.get(filePath).getParent();
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.copy(screenshotFile.toPath(), new File(filePath).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
