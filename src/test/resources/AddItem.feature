@AddItem
Feature: Add Item

  Scenario: Add a single item to the cart
    Given user open login page
    And user login with username and password
    And user will redirect to homepage
    And user add item to the cart
    Then the cart icon should show "1"

  Scenario: View cart after adding items
    Given user open login page
    And user login with username and password
    And user will redirect to homepage
    And user add item to the cart
    And user click on cart icon
    Then user will redirect to cart page

