@UI
Feature: Complete Registration Functionality

  @Registration
  Scenario: Registration functionality
    Given Launch Registration form with URL "https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html"
    When user enter all details for registration
    Then verify confirmation message is displayed
