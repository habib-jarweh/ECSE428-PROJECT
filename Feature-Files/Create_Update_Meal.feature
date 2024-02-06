Feature: Create and Update Meal in SmartEats

	As a user of SmartEats,
	I want to add and update my Meals,
	so that I can personalize my SmartEats experience with meals that I enjoy.

	Scenario Outline: Successfully Adding a new Meal (Normal Flow)
		Given I am logged into my SmartEats admin account
		When I navigate to the Edit Available Meals section
		And I add a new Meal "<meal>"
		Then the new meal should be saved to the Available Meals list
		And I should see a confirmation message

	Scenario Outline: Successfully Updating an Existing Meal (Normal Flow)
		Given I am logged into my SmartEats admin account
		And I have previously added a Meal "<existing_meal>"
		When I update the meal to "<new_meal>"
		Then the meal should be updated in the Available Meals list
		And I should see a confirmation message

	Scenario Outline: Adding a Duplicate Meal Name (Error Handling)
		Given I am logged into my SmartEats admin account
		And I have already added a Meal "New Meal"
		When I attempt to add the same Meal "New Meal" again
		Then I should see an error message indicating the Meal with name "The Meal with name 'New Meal' already exists"
		And the duplicate meal should not be added to the Available Meals list

	Scenario Outline: Successfully Remove Meal (Normal Flow)
		Given I am logged into my SmartEats admin account
		And I have previously added a Meal "<existing_meal>"
		When I attempt to delete the Meal
		Then the meal should be removed from the Available Meals list 
		And I should see a confirmation message


	Scenario Outline: Successfully Hide Meal (Normal Flow)
		Given I am logged into my SmartEats admin account
		And I have previously added a Meal "<existing_meal>"
		When I attempt to hide the Meal from the customer users
		Then the meal should be labeled as 'Hidden'
		And the meal should be able to be viewed or ordered by customer users
		And I should see a confirmation message
