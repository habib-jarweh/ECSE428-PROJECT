Feature: Add/ Update Weight Goal
  
As a user, I want to be able to add or change my weight goal in my account, so that I can track my weight changes.

Scenario: Add weight goal (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Add weight Goal" button
    And the user adds his weight goal
    And the user clicks on the "Done" button
    Then the user should see a success message indicating that his/her weight goal has been added
    And his/her weight goal should be displayed on his/her account page

Scenario: Change existing weight goal (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Edit Weight Goal" button
    And the user adds a new weight goal
    And the user clicks on the "Done" button
    Then the user should see a success message indicating that his/her weight goal has been updated
    And his/her new weight goal should be displayed on his/her account page

Scenario: Upload incorrect weight goal (Error Handling)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Edit Weight Goal" button
    And the user selects an incorrect weight goal
    And the user clicks on the "	Done" button
    Then the user should see an error message indicating that the weight added failed
    And his/her account page should not display any changes to the weight goal

Scenario: Remove weight goal (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Remove Weight Goal" button
    Then the user should see a confirmation message asking if the user want to remove his/her weight goal
    And the user clicks on the "Confirm" button
    Then the user should see a success message indicating that his/her weight goal has been removed
    And his/her account page should no longer display a weight goal
