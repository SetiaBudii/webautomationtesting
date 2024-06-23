Feature: Login Functionality

  Background:
    Given I am on the login page

  Scenario: Successful login
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be logged in successfully

  Scenario: Unsuccessful login with invalid credentials
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see an error message "<message>"

    Examples:
    | username         | password         | message                                                                   |
    | invalid_user     | invalid_password | Username and password do not match any user in this service !             |
    | standard_user123 | secret_sauce     | Username and password do not match any user in this service !             |
    |                  | invalid_password | You need Username !                                                       |
    | standard_user123 |                  | You need Password !                                                       |
    |                  |                  | You need Username and Password !                                          | 