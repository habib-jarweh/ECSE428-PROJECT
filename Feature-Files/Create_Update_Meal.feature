Feature: Create and Update Meal in SmartEats

	As a user of SmartEats,
	I want to add and update my Meals,
	so that I can personalize my SmartEats experience with meals that I enjoy.

	Scenario Outline: Successfully Adding a new Meal (Normal Flow)
		Given I am logged into my SmartEats account
		When I navigate to the My Meals section
		And I add a new Meal "<meal>"
		Then the new meal should be saved to my profile
		And I should see a confirmation message

	Scenario Outline: Successfully Updating an Existing Meal (Normal Flow)
		Given I am logged into my SmartEats account
		And I have previously added a Meal "<existing_meal>"
		When I update the meal to "<new_meal>"
		Then the meal should be updated in my profile
		And I should see a confirmation message

	Scenario Outline: Adding a Duplicate Meal Name (Error Handling)
		Given I am logged into my SmartEats account
		And I have already added a Meal "My Meal"
		When I attempt to add the same Meal "My Meal" again
		Then I should see an error message indicating the Meal with name "My Meal already exists"
		And the duplicate meal should not be added to my profile

	Scenario Outline: Successfully Remove Meal (Normal Flow)
		Given the user is logged into my SmartEats account
		And I have previously added a Meal "<existing_meal>"
		When I attempt to delete the Meal
		Then the meal should be removed from my profile
		And I should see a confirmation message

