@AddItem
Feature: Add Item

  Background:
    Given user open the login page
    And user login with username "standard_user" and password "secret_sauce"

  @addItem
  Scenario: Add a single item to the cart
    When user add item to the cart
    Then the cart icon should show "1"

  @addItem
  Scenario: View cart after adding items
    When user add item to the cart
    And user click on cart icon
    Then user will redirect to cart page

