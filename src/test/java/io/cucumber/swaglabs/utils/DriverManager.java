// package io.cucumber.swaglabs.utils;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;

// public class DriverManager {
//   private static WebDriver driver;

//   public static WebDriver getDriver() 
//   {
//     if (driver == null) {
//       driver = new ChromeDriver();
//     }
//     return driver;
//   }

//   public static void closeDriver() 
//   {
//     if (driver != null) {
//       driver.quit();
//       driver = null;
//     }
//   }
// }
package io.cucumber.swaglabs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static WebDriver driver;
    private static final String SELENIUM_GRID_URL = "http://103.63.25.132:4444/wd/hub"; // Change to your Selenium Grid URL

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                // Use ChromeOptions instead of DesiredCapabilities
                ChromeOptions options = new ChromeOptions();
                driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to create WebDriver", e);
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
