Feature: Self-Service User Account Deletion

As a user of SmartEats, I want to delete my user account to ensure my data is removed from the system for privacy reasons.

Background:
    Given I am logged in to SmartEats

Scenario: Deleting Own Account Successfully (Normal Flow)
    When I request to delete my account
    Then I should get a confirmation from the system of the deletion request
    And my account is successfully deleted
    And all my data associated with SmartEats is removed from the system

Scenario: Attempting to Delete Account without Confirmation (Error Flow)
    When I request to delete my account
    But I do not confirm the deletion request
    Then I should not process with the account deletion
    And I should see a display message asking me to confirm the deletion request



