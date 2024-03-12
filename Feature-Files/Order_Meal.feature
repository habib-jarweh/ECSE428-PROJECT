Feature: Meal Browsing and Order History Viewing

As a customer of SmartEats, 
I want browse different available meals and add my desired meals that aligns with my dietary goals. 
In addition, I would like to see current and past orders.

Background:
    Given I am logged in to SmartEats

Scenario: Browsing Meals and Filtering by Dietary Goals (Normal Flow)
    When I browse the meal options
    Then I should see the system displays all available meals
    When I apply filters based on my dietary goals
    Then I should be displayed with only the meals that align with the specified dietary goals

Scenario: Adding a Meal to the Cart (Normal Flow)
    Given I have applied filters and found a desired meal
    When I add the meal to my cart
    Then the system confirms the meal has been added to the cart

Scenario: Viewing Current and Past Orders (Normal Flow)
    When I request to view their order history
    Then the system displays the current and past orders
    And includes details such as order date, meals ordered, and order status

Scenario: Attempting to Add a Meal Without Meeting Dietary Preferences (Error Flow)
    Given I have set dietary goals
    When I try to add a meal not aligning with these goals
    Then the system warns me about the mismatch
    And suggests meals that align with the dietary goals





