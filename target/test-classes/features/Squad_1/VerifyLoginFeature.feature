@P1

Feature: [Squad-1] [Login Feature] [TestCaseID-001] Verify the login functionality of the application under test

 Scenario Outline: Login with invalid username and password
    When I enter username as "<username>"
    And I enter password as "<password>"
    And I tap on Login button
    Then Login should fail with an error message "<errmsg>"

    Examples:
      | username             |password         |errmsg                                                      |
      |invalidUsername       |secret_sauce     |Username and password do not match any user in this service|
      |standard_user         |invalidPassword  |Username and password do not match any user in this service.|


  Scenario Outline: Login with valid username and password
    When I enter username as "<username>"
    And I enter password as "<password>"
    And I tap on Login button
    Then I should see Products screen with "<title>"
    And  I should be able to log out of the app

    Examples:
      | username           |password         |title   |
      |standard_user       |secret_sauce     |PRODUCTS|