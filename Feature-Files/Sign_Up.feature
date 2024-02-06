Feature: SmartEats User Account Creation

  As a prospecive user of SmartEats,
  I want to create an account,
  So that I can access personalized meal plans and track my dietary progress.

  Scenario: Successful Account Creation
    Given I have accessed the SmartEats registration page
    When I enter valid registration details including my name, email, and dietary goal
    And I submit the registration form
    Then my account should be successfully created
    And I should receive a confirmation email with login details
    Examples:
      | name            | email                  | dietary_goal       | user_id | password |
      | Alex Johnson    | alexj@example.com      | Weight Loss        | AJ001   | ajpass   |
      | Jamie Lee       | jamie.lee@example.com  | Muscle Gain        | JL002   | jlpass   |
      | Sam Rivera      | samr@example.com       | Healthy Lifestyle  | SR003   | srpass   |
      | Casey Kim       | casey.kim@example.com  | Managing Condition | CK004   | ckpass   |
      | Morgan Bailey   | morganb@example.com    | Variety in Diet    | MB005   | mbpass   |

  Scenario: Attempt to Create an Account with an Existing Email
    Given I have accessed the SmartEats registration page
    When I enter an email that is already associated with an existing account
    And I submit the registration form
    Then I should see a message indicating that the email is already in use
    And I should be prompted to either log in or recover my password

  Scenario: Attempt to Create an Account with Invalid Details
    Given I have accessed the SmartEats registration page
    When I enter invalid details, such as an incorrect email format
    And I submit the registration form
    Then I should see a message indicating the invalid details
    And I should be asked to correct the errors before submitting again
