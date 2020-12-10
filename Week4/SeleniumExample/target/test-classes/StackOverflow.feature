#Feature: Search India on BBC website and verify search.
#
  #@Search
  #Scenario: Search India on BBC website and verify it.
    #Given I open the firefox browser
    #And I navigating to BBc website
    #Then I click at search textbox
    #And I enter "India" in search textbox
    #And I click at Search button
    #Then I should be taken to search page
    #And I verify India on search page
#

#
Feature: Poking Stack Overflow!!

  
  Scenario: Test the about link
    Given On stackoverflow landing page
    When The "about" is clicked
    Then Should be taken to about page

  #
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
