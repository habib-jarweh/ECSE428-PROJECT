Feature: Meal Browsing and Order History Viewing

As a customer, 
I want browse different available meals and add my desired meals that aligns with my dietary goals. 
In addition, I would like to see current and past orders.

Background:
    Given the Customer "CustomerUser" is logged in

Scenario: Browsing Meals and Filtering by Dietary Goals (Normal Flow)
    When "CustomerUser" browses the meal options
    Then the system displays all available meals
    When "CustomerUser" applies filters based on dietary goals
    Then the system displays only the meals that align with the specified dietary goals

Scenario: Adding a Meal to the Cart (Normal Flow)
    Given "CustomerUser" has applied filters and found a desired meal
    When "CustomerUser" adds the meal to their cart
    Then the system confirms the meal has been added to the cart

Scenario: Viewing Current and Past Orders (Normal Flow)
    When "CustomerUser" requests to view their order history
    Then the system displays the current and past orders
    And includes details such as order date, meals ordered, and order status

Scenario: Attempting to Add a Meal Without Meeting Dietary Preferences (Error Flow)
    Given "CustomerUser" has set dietary goals
    When "CustomerUser" tries to add a meal not aligning with these goals
    Then the system warns "CustomerUser" about the mismatch
    And suggests meals that align with the dietary goals





