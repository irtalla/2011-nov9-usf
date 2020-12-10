# Feature file for NeoPets home page
Feature:  NeoPets home page works

# Background: Given I am on the NeoPets home page

Scenario Outline: Clicking on merchandise link works
	Given I am on the NeoPets home page
	When I click on the merchandice link
	And 
	Then the merchandise link should navigate to a new page
	
	Examples:
		|		username		|		password		|
		|		pokemon   		| 		pokemon				|
		|		santa		|		santa				|
		
Scenario: View Events page shows up
	Given I am on the NeoPets home page
	When I click on the View Events link
	Then the header will say View Cats
	
Scenario Outline: The nav bar works
	Given I am on the NeoPets home page
	And I am logged in
	When I click on the "<link text>" link
	Then the header will say "<link text>"
	
	Examples:
		|		link text		|
		|		View Cats		|
		|		My Cats			|
	
	
