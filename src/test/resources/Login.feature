@login
Feature: Login

  @valid-login
  Scenario: Login using valid username and password
    Given user in on login page
    When user input email text box with "standard_user"
    And user input password text box with "secret_sauce"
    And user click login button
    Then user will redirect to homepage

  @invalid-login
  Scenario: Login using invalid username
    Given user in on login page
    When user input email text box with "sinta"
    And user input password text box with "secret_sauce"
    And user click login button
    Then user will see error message

  @invalid-login
  Scenario: Login using wrong password
    Given user in on login page
    When user input email text box with "standard_user"
    And user input password text box with "12345"
    And user click login button
    Then user will see error message

  @invalid-login
  Scenario: Login using blank username
    Given user in on login page
    When user input email text box with ""
    And user input password text box with "secret_sauce"
    And user click login button
    Then user will see error message

  @invalid-login
  Scenario: Login using blank password
    Given user in on login page
    When user input email text box with "standard_user"
    And user input password text box with ""
    And user click login button
    Then user will see error message