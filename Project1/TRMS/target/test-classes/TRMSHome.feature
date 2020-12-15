# Feature file for TRMS home page
Feature: TRMS home page works

# Background: Given I am on the TRMS home page

Scenario Outline: Logging in works
	Given I am on the TRMS home page
	When I enter "<username>" and "<password>" to log in
	And I click the login button
	Then the username link should contain "<username>"
	
	Examples:
		|		username		|		password		|
		|		pokemon			| 	pokemon				|
		|		ash_kutchem		|		ash_kutchem				|
		
Scenario: View Cats page shows up
	Given I am on the TRMS home page
	When I click on the View Events link
	Then the header will say View Events
	
Scenario Outline: The nav bar works
	Given I am on the TRMS home page
	And I am logged in
	When I click on the "<link text>" link
	Then the header will say "<link text>"
	
	Examples:
		|		link text		|
		|		View Events		|
		|		My Events			|
	
	
