# Feature file for Louis XIII
Feature: Louis XII has a father with a signature

# Background: Given I am on the Louis XIII page on wikipedia

#Scenario: I can get Louis XIIIs predecessor
	#Given I am on the Louis XIII page on wikipedia
	#When I click on Henry XIVs name next to predecessor
	#Then we should be on Henry_XIV,_Duke_of_Bavarias page on wikipedia
		
Scenario: Close out of signature
	When I press the escape key
	Given I am viewing Henry XIVs signature
	Then I return to Henry XIVs page
