# Feature file for CatApp home page
Feature: SPM App works

# Background: Given I am on the index page

Scenario: Logging in works
	Given I am on the SPM App home page
	When I enter "hi" to the username input
	And I enter "hi" to the password input
	And I click on "Log In"
	Then the nav bar should contain "Log Out"

Scenario Outline: Registering works
	Given I am on the SPM App home page
	When I enter "yo" to the username input
	And I enter "yo" to the email input
	And I enter "yo" to the password input
	And I click on "Register"
	Then the nav bar should contain "Log Out"	
	
	Examples:
	|fred | fred | fred|