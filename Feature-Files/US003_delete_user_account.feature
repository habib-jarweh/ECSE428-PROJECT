Feature: Self-Service User Account Deletion

As a user, I want to delete my user account to ensure my data is removed from the system for privacy reasons.

Background:
    Given the User "RegularUser" is logged in

Scenario: Deleting Own Account Successfully (Normal Flow)
    When "RegularUser" requests to delete their own account
    Then the system should confirm the deletion request
    And the account of "RegularUser" is successfully deleted
    And all data associated with "RegularUser" is removed from the system

Scenario: Attempting to Delete Account without Confirmation (Error Flow)
    When "RegularUser" requests to delete their own account
    But "RegularUser" does not confirm the deletion request
    Then the system should not proceed with the account deletion
    And display a message asking "RegularUser" to confirm the deletion request



