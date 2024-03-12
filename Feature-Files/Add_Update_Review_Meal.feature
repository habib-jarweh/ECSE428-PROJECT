Feature: Adding, Updating Meal Reviews

As a customer, 
I want to add a review to a meal to share my experience and help others make informed meal choices.

Background:
    Given I am logged in to SmartEats
    And I have ordered a meal

Scenario: Adding a Review for a Previously Ordered Meal (Normal Flow)
    When I select a previously ordered meal to review
    And I write a review and assigns a rating
    Then I submit the review
    Then the system confirms the review has been added
    And the review is displayed to other customers browsing the meal

Scenario: Attempting to Review a Meal Not Ordered (Error Flow)
    When I attempt to review a meal they have not ordered
    Then the system does not allow me to write a review
    And displays a message explaining that only meals that have been ordered can be reviewed

Scenario: Editing an Existing Review (Alternative Flow)
    Given I have submitted a review for a meal
    When I selects the review to edit
    And I makes changes to the review and rating
    Then I submit the updated review
    Then the system confirms the review has been updated
    And the updated review is displayed to other customers

Scenario: Attempting to Add a Review Without a Rating (Error Flow)
    When I write a review without assigning a rating
    Then the system prompts me to assign a rating before submitting the review

