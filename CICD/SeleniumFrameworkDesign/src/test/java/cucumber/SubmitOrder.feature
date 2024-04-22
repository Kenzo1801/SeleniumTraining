
@tag
Feature: Purchase the order from eCommerce Website
  I want to use this template for my feature file

	Background:
		Given I landed on eCommerce Page
  
  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name            | password | productName |
      | kameni@gmail.com | Ulrich&ka1 | ZARA COAT 3 |

