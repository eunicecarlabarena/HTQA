Feature: Login Feature

  Scenario Outline: Test Login with valid credentials
    Given User navigates to login page
    When User enters valid <username> and <password>
    And Clicks on login button
    Then User should be redirected to homepage

  Examples:
  	| username | password |
  	| eunice.barena+mcabi@hometime.io	| cvW8qx4B2o1WegCEDy41Xg==	|
  	|	freia.dumandan+mcabi@hometime.io| cvW8qx4B2o1WegCEDy41Xg==	| 
    