Feature: View/Edit User Information

As a user, I want to view and edit my personal credentials, such as address, billing information and changing my profile picture.
As a User, I want to view and edit my personal credentials while signed in

Background:
    Given the User "UserProfileUser" is logged in

Scenario: Viewing Personal Credentials (Normal Flow)
    When "UserProfileUser" navigates to their profile settings
    Then the system displays the current address, billing information, and profile picture

Scenario: Editing Address Information (Normal Flow)
    Given "UserProfileUser" is viewing their personal credentials
    When "UserProfileUser" selects to edit their address
    And "UserProfileUser" enters a new address and saves the changes
    Then the system updates the user's address information
    And confirms the update to "UserProfileUser"

Scenario: Updating Billing Information (Normal Flow)
    Given "UserProfileUser" is viewing their personal credentials
    When "UserProfileUser" selects to edit their billing information
    And "UserProfileUser" updates their billing details and saves the changes
    Then the system updates the user's billing information
    And confirms the update to "UserProfileUser"

Scenario: Changing Profile Picture (Normal Flow)
    Given "UserProfileUser" is viewing their personal credentials
    When "UserProfileUser" selects to change their profile picture
    And "UserProfileUser" uploads a new profile picture and saves the change
    Then the system updates the user's profile picture
    And confirms the update to "UserProfileUser"

Scenario: Attempting to Save Invalid Billing Information (Error Flow)
    Given "UserProfileUser" is editing their billing information
    When "UserProfileUser" enters invalid billing details
    And attempts to save the changes
    Then the system displays an error message indicating the billing information is invalid
    And prompts "UserProfileUser" to correct the information