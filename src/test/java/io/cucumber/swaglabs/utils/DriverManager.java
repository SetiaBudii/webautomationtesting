// package io.cucumber.swaglabs.utils;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.Dimension;

// public class DriverManager {
//   private static WebDriver driver;

//   public static WebDriver getDriver() 
//   {
//     if (driver == null) {
//       ChromeOptions options = new ChromeOptions();
//       driver = new ChromeDriver(options);
//       driver.manage().window().maximize();  
//       driver.manage().window().setSize(new Dimension(2048, 1080));
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                driver = new RemoteWebDriver(new URL("http://localhost:8888"), capabilities);
                driver.manage().window().fullscreen();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize WebDriver", e);
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

