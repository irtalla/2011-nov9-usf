Feature: WRTMJ home page works

Scenario Outline: Searching works
	Given I am on the WRTMJ home page
	When I enter "<job>" to search
	And I click on the first option
	Then the percentage should be "<percent>"
	
	Examples:
		|	job											 |	percent	|
		|	Software Developer	     |	13%	    |
		| Accountants and Auditors |  94%     |
		| Funeral Attendants       |  37%     |
		| Lawyers                  |  4%      |
		