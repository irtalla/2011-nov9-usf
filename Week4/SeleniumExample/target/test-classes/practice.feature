# Feature file for YouTube home page
Feature: Youtube home page works

# Background: Given I am on the YouTube home page

		
Scenario: Search bar shows up
	Given I am on the Youtube home page
	When I click on the search bar
	Then previous searches should appear
	
Scenario Outline: The input tag works
	Given I am on the Youtube home page
	When I "<search text>"
	Then the header will say "<search text>"
	
	Examples:
		|		search text		|
		|		steak			|
		|		potatoes		|
	
	