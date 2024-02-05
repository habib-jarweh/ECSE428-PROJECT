Feature: Sign In to SmartEats Application

  As a registered user of SmartEats,
  I want to sign in to my account,
  So that I can access personalized meal plans and track my dietary progress.

  Scenario Outline: Successful Sign-In
    Given I am on the SmartEats sign-in page
    When I enter a valid email "<email>" and password "<password>"
    And I click on the sign-in button
    Then I should be redirected to my dashboard

    Examples:
      | email                  | password |
      | alexj@example.com      | ajpass   |
      | jamie.lee@example.com  | jlpass   |
      | samr@example.com       | srpass   |

  Scenario: Sign-In with Incorrect Credentials
    Given I am on the SmartEats sign-in page
    When I enter an incorrect email or password
    And I click on the sign-in button
    Then I should see an error message indicating incorrect credentials
    And I should be asked to try again or reset my password

  Scenario: Sign-In with Unregistered Email
    Given I am on the SmartEats sign-in page
    When I attempt to sign in with an email that is not registered
    And I click on the sign-in button
    Then I should see a message indicating the account does not exist
    And I should be given the option to register or try again

  Scenario: Sign-In with a Deactivated Account
    Given I am on the SmartEats sign-in page
    When I enter the email and password of a deactivated account
    And I click on the sign-in button
    Then I should see a message indicating the account has been deactivated
    And I should be directed to contact support for further assistance
