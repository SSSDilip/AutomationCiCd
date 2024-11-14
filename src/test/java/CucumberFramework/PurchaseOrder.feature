
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given I Logged in with userName <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on the ConfirmationPage

    Examples: 
      | name  									| password						 | productName  |
      | sssdilip101@gmail.com 	| Dilip@123						 | ZARA COAT 3  |
      
