# Feature file for CatApp home page
Feature: Cucumber Wiki home page works

# Background: Given I am on the Cucumber Wiki home page

Scenario Outline: Logging in works
	Given I am on the CucumberWiki home page
	When I click on "Random article"
	And I enter "piracy" to the search input
	Then the heading should contain "piracy"
	
	Examples:
		|		piracy		|
	
	