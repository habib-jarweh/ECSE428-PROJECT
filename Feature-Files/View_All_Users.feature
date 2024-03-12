Feature: Admin View of Active Customer Accounts and Account Blocking

As an admin, I want to see the customers with active accounts in the system,
so I can block a customer's account.

Background:
    Given the Administrator "AdminUser" is logged in

Scenario: Viewing Customers with Active Accounts (Normal Flow)
    When "AdminUser" navigates to the user management section
    Then the system displays a list of customers with active accounts

Scenario: Blocking a Customer's Account (Normal Flow)
    Given "AdminUser" is viewing the list of customers with active accounts
    When "AdminUser" selects a customer account to block
    And "AdminUser" confirms the action to block the account
    Then the system blocks the selected customer's account
    And confirms the account blocking to "AdminUser"

Scenario: Attempting to Block an Already Blocked Account (Error Flow)
    Given "AdminUser" is viewing the list of customers with active accounts
    When "AdminUser" selects an account that is already blocked
    And attempts to block it again
    Then the system displays an error message indicating the account is already blocked

Scenario: Searching for a Specific Customer Account (Alternative Flow)
    Given "AdminUser" is in the user management section
    When "AdminUser" searches for a customer by name or email
    Then the system displays the search results
    And "AdminUser" can select a customer account from the search results to block