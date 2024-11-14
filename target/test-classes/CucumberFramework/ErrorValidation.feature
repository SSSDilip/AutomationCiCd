
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page
    When I Logged in with userName <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  									| password						 |
      | sssdilip101@gmail.com 	| Dilip@1231						 |
