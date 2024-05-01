@UI
Feature: Add vegetables to cart
  and Proceed with payment

  @GreenCart
  Scenario: GreenKart Shoping
    Given Lauch GreenKart Browser with URL "https://rahulshettyacademy.com/seleniumPractise/"
    When Add vegetables To Cart
      | Cucumber    |
      | Mushroom    |
      | Pomegranate |
      | Brocolli    |
    And Click on Proceed To checkout
    And Validate the Items selected
    And Apply Promo code "rahulshettyacademy"
    Then Proceed with Payment
