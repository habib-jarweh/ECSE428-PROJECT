Feature: Sign Out

  As a user of SmartEats, I would like to be able to logout from the system at anytime, so that I can login with a different account or create a new one

  Background:
    Given that I am a registered user of SmartEats
    And I am logged into SmartEats

    Scenario: Logout from SmartEats (Normal Flow)
      Given the user is logged into SmartEats
      When the user requests to logout
      Then the system logs the user out
      And the session of the user is terminated
      And the login page is displayed
