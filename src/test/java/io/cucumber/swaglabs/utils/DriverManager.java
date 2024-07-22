// // // // package io.cucumber.swaglabs.utils;

// // // // import org.openqa.selenium.WebDriver;
// // // // import org.openqa.selenium.chrome.ChromeDriver;

// // // // public class DriverManager {
// // // //   private static WebDriver driver;

// // // //   public static WebDriver getDriver() 
// // // //   {
// // // //     if (driver == null) {
// // // //       driver = new ChromeDriver();
// // // //     }
// // // //     return driver;
// // // //   }

// // // //   public static void closeDriver() 
// // // //   {
// // // //     if (driver != null) {
// // // //       driver.quit();
// // // //       driver = null;
// // // //     }
// // // //   }
// // // // }
// package io.cucumber.swaglabs.utils;

// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.chrome.ChromeOptions;
// import org.openqa.selenium.remote.RemoteWebDriver;

// import java.net.MalformedURLException;
// import java.net.URL;

// public class DriverManager {
//     private static WebDriver driver;
//     private static final String SELENIUM_GRID_URL = "http://103.63.25.132:4444/wd/hub"; // Change to your Selenium Grid URL

//     public static WebDriver getDriver() {
//         if (driver == null) {
//             try {
//                 // Use ChromeOptions instead of DesiredCapabilities
//                 ChromeOptions options = new ChromeOptions();
//                 driver = new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
//                 driver.manage().window().maximize();
//                 driver.manage().window().setSize(new org.openqa.selenium.Dimension(2560, 1440));
//             } catch (MalformedURLException e) {
//                 e.printStackTrace();
//                 throw new RuntimeException("Failed to create WebDriver", e);
//             }
//         }
//         return driver;
//     }

//     public static void closeDriver() {
//         if (driver != null) {
//             driver.quit();
//             driver = null;
//         }
//     }
// }

// // package io.cucumber.swaglabs.utils;

// // import org.openqa.selenium.WebDriver;
// // import org.openqa.selenium.chrome.ChromeDriver;
// // import org.openqa.selenium.chrome.ChromeOptions;
// // import org.openqa.selenium.Dimension;

// // public class DriverManager {
// //   private static WebDriver driver;

// //   public static WebDriver getDriver() 
// //   {
// //     if (driver == null) {
// //       ChromeOptions options = new ChromeOptions();
// //       driver = new ChromeDriver(options);
// //       driver.manage().window().maximize();  
// //       driver.manage().window().setSize(new Dimension(2048, 1080));
// //     }
// //     return driver;
// //   }

// //   public static void closeDriver() 
// //   {
// //     if (driver != null) {
// //       driver.quit();
// //       driver = null;
// //     }
// //   }
// // }

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
                driver = new RemoteWebDriver(new URL("http://184.105.238.72:8888"), capabilities);
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

