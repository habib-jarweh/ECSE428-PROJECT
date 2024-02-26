Feature: Admin Manage Meals

As an admin, I want to add a meal in the system, 
so the customers can view the meals when browsing for different meals

Background:
    Given the Administrator "AdminUser" is logged in

Scenario: Adding a New Meal (Normal Flow)
    When "MealAdmin" navigates to the meal management section
    And "MealAdmin" selects the option to add a new meal
    And "MealAdmin" enters the meal details including name, description, price, dietary information, and an image
    And "MealAdmin" saves the new meal
    Then the system confirms the meal has been added
    And the new meal is available for customers to view in the meal listings

Scenario: Updating an Existing Meal (Normal Flow)
    Given "MealAdmin" is viewing the meal management section
    When "MealAdmin" selects an existing meal to update
    And "MealAdmin" modifies the meal details such as name, description, price, or dietary information
    And "MealAdmin" saves the updates
    Then the system confirms the meal has been updated
    And the updated meal details are reflected in the customer meal listings

Scenario: Attempting to Add a Meal with Incomplete Information (Error Flow)
    When "MealAdmin" attempts to add a new meal
    And "MealAdmin" leaves some required fields empty, such as the meal name or price
    And attempts to save the meal
    Then the system displays an error message indicating the missing information
    And prompts "MealAdmin" to complete all required fields

Scenario: Removing a Meal from Listings (Alternative Flow)
    Given "MealAdmin" is in the meal management section
    When "MealAdmin" selects an existing meal to remove from listings
    And "MealAdmin" confirms the removal
    Then the system removes the meal from the customer view
    And confirms to "MealAdmin" that the meal is no longer listed