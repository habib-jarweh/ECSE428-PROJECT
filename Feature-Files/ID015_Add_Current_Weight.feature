Feature: Add Current Weight 
  
As a user, I want to be able to add or change my weight in my account, so that I can track my weight changes.

Scenario: Add current weight (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Add weight" button
    And the user adds his current weight 
    And the user clicks on the "Done" button
    Then the user should see a success message indicating that his/her current weight has been added
    And his/her weight should be displayed on his/her account page

Scenario: Change existing current weight (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Edit Weight" button
    And the user adds a new weight
    And the user clicks on the "Done" button
    Then the user should see a success message indicating that his/her weight has been updated
    And his/her new weight should be displayed on his/her account page

Scenario: Upload incorrect weight (Error Handling)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Edit weight" button
    And the user selects an incorrect weight
    And the user clicks on the "	Done" button
    Then the user should see an error message indicating that the weight added failed
    And his/her account page should not display any changes to the weight 

Scenario: Remove weight (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Remove weight" button
    Then the user should see a confirmation message asking if the user want to remove his/her weight
    And the user clicks on the "Confirm" button
    Then the user should see a success message indicating that his/her weight has been removed
    And his/her account page should no longer display a current weight 
