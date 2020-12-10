# Feature file for CatApp home page
Feature: Cat App home page works

# Background: Given I am on the CatApp home page

Scenario Outline: Logging in works
	Given I am on the CatApp home page
	When I enter "<username>" and "<password>" to log in
	And I click the login button
	Then the username link should contain "<username>"
	
	Examples:
		|		username		|		password		|
		|		sierra			| 	pass				|
		|		revature		|		pass				|
		
Scenario: View Cats page shows up
	Given I am on the CatApp home page
	When I click on the View Cats link
	Then the header will say View Cats
	
Scenario Outline: The nav bar works
	Given I am on the CatApp home page
	And I am logged in
	When I click on the "<link text>" link
	Then the header will say "<link text>"
	
	Examples:
		|		link text		|
		|		View Cats		|
		|		My Cats			|
	
	