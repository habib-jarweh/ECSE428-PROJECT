Feature: Adding, Updating Meal Reviews

As a customer, 
I want to add a review to a meal to share my experience and help others make informed meal choices.

Background:
    Given the Customer "CustomerUser" is logged in
    And "CustomerUser" has ordered a meal

Scenario: Adding a Review for a Previously Ordered Meal (Normal Flow)
    When "CustomerUser" selects a previously ordered meal to review
    And "CustomerUser" writes a review and assigns a rating
    Then "CustomerUser" submits the review
    Then the system confirms the review has been added
    And the review is displayed to other customers browsing the meal

Scenario: Attempting to Review a Meal Not Ordered (Error Flow)
    When "CustomerUser" attempts to review a meal they have not ordered
    Then the system does not allow "CustomerUser" to write a review
    And displays a message explaining that only meals that have been ordered can be reviewed

Scenario: Editing an Existing Review (Alternative Flow)
    Given "CustomerUser" has submitted a review for a meal
    When "CustomerUser" selects the review to edit
    And "CustomerUser" makes changes to the review and rating
    Then "CustomerUser" submits the updated review
    Then the system confirms the review has been updated
    And the updated review is displayed to other customers

Scenario: Attempting to Add a Review Without a Rating (Error Flow)
    When "CustomerUser" writes a review without assigning a rating
    Then the system prompts "CustomerUser" to assign a rating before submitting the review

