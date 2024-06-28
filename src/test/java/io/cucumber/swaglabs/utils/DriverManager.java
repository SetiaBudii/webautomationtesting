package io.cucumber.swaglabs.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
  private static WebDriver driver;

  public static WebDriver getDriver() 
  {
    if (driver == null) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--start-maximized");      
      driver = new ChromeDriver(options);
      driver.manage().window().maximize();  
    }
    return driver;
  }

  public static void closeDriver() 
  {
    if (driver != null) {
      driver.quit();
      driver = null;
    }
  }
}
