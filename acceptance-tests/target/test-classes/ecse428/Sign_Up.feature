Feature: SmartEats User Account Creation

  As a prospecive user of SmartEats,
  I want to create an account,
  So that I can access personalized meal plans and track my dietary progress.

  Scenario: Successful Account Creation
    Given the backend is running
    And the SmartEats registration page is accessed
    When entering valid registration details
    And the registration form is submitted
    Then the account should be successfully created


  Scenario: Attempt to Create an Account with an Existing Email
    Given the SmartEats registration page is accessed
    When an email that is already associated with an existing account is entered
    And the registration form is submitted
    Then a message indicating that the email is already in use should be received

  Scenario: Attempt to Create an Account with Invalid Email
    Given the SmartEats registration page is accessed
    When an incorrect email format is entered
    And the registration form is submitted
    Then a message indicating invalid details should be received

  Scenario: Attempt to Create an Account with Invalid Password
    Given the SmartEats registration page is accessed
    When an invalid password format is entered
    And the registration form is submitted
    Then a message indicating invalid password should be received
