Feature: Add Profile Picture
  
As a user, I want to be able to add or change my profile picture to my account, so that others and including myself can see my profile picture.

Scenario: Upload profile picture (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Upload Picture" button
    And the user selects a picture file from his/her device
    And the user clicks on the "Upload" button
    Then the user should see a success message indicating that his/her profile picture has been uploaded
    And his/her profile picture should be displayed on his/her account page

Scenario: Change existing profile picture (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Change Picture" button
    And the user selects a new picture file from his/her device
    And the user clicks on the "Upload" button
    Then the user should see a success message indicating that his/her profile picture has been changed
    And his/her new profile picture should be displayed on his/her account page

Scenario: Upload incorrect profile picture (Error Handling)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Upload Picture" button
    And the user select an incorrect picture file from his/her device
    And the user clicks on the "Upload" button
    Then the user should see an error message indicating that the profile picture upload failed
    And his/her account page should not display any changes to the profile picture

Scenario: Remove profile picture (Normal Flow)
    Given the user is logged in to his/her SmartEats account
    When the user navigates to the profile settings page
    And the user clicks on the "Remove Picture" button
    Then the user should see a confirmation message asking if the user want to remove his/her profile picture
    And the user clicks on the "Confirm" button
    Then the user should see a success message indicating that his/her profile picture has been removed
    And his/her account page should no longer display a profile picture
