Feature: SmartEats User Sign In

As a current user of SmartEats,
  I want to sign in to my existing account,
  So that I can access personalized meal plans and track my dietary progress.
 
 Scenario: Successful Sign-In
    Given the backend is running
    And the SmartEats sign-in page is accessed    
    When a valid email and password are entered
    And the sign-in form is submitted
    Then sign-in should be successful

  Scenario: Sign-In with Incorrect Credentials
    Given the SmartEats sign-in page is accessed
    When an incorrect email or password is used to sign in
    Then an error message indicating incorrect credentials should be received

  Scenario: Sign-In with Unregistered Email
    Given the SmartEats sign-in page is accessed
    When an unregistered email is used to sign in
    And the sign-in form is submitted
    Then an error message indicating that the account is invalid should be received

  Scenario: Sign-In with a Deactivated Account
    Given the SmartEats sign-in page is accessed
    When a deactivated account's email is used to sign in
    And the sign-in form is submitted
    Then an error message indicating the account has been deactivated should be received
