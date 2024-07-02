package io.cucumber.swaglabs.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
// import io.cucumber.swaglabs.pages.LoginPage;
import io.cucumber.swaglabs.pages.actions.loginActions;
// import io.cucumber.swaglabs.steps.Hooks;


public class LoginSteps {
  // LoginPage objLoginPage = new LoginPage(Hooks.driver);
  loginActions objLoginPage = new loginActions(Hooks.driver);

  @Given("I am on the login page")
  public void iAmOnTheLoginPage() {
    Hooks.driver.get("https://www.saucedemo.com/");
    Hooks.driver.manage().window().maximize();
    Hooks.driver.manage().window().setSize(new org.openqa.selenium.Dimension(2048, 1080));
  }

  @When("I enter username {string} and password {string}")
  public void iEnterUsernameAndPassword(String username, String password) {
    objLoginPage.enterUsername(username);
    objLoginPage.enterPassword(password);
  }

  @And("I click the login button")
  public void iClickTheLoginButton() {
    objLoginPage.clickLoginButton();
  }

  @Then("I should be logged in successfully")
  public void iShouldBeLoggedInSuccessfully() {
    String expectedUrl = "https://www.saucedemo.com/inventory.html";
    String actualUrl = Hooks.driver.getCurrentUrl();
    assertEquals(expectedUrl, actualUrl);
  }

  @Then("I should see an error message {string}")
  public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
    String actualErrorMessage = objLoginPage.getMessage();
    assertEquals(expectedErrorMessage, actualErrorMessage);
  }
}
