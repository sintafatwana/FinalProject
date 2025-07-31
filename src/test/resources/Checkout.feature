@Checkout
  Feature:
    Background:
      Given user open the login page
      And user login with username "standard_user" and password "secret_sauce"


    @invalid-checkout
    Scenario: Checkout using blank first name field
      When user add item "Sauce Labs Backpack" to the cart
      And user click cart icon on homepage
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with ""
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button
      Then user will see error message "Error: First Name is required"

    @invalid-checkout
    Scenario: Checkout using blank last name field
      When user add item "Sauce Labs Backpack" to the cart
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with ""
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button
      Then user will see error message "Error: Last Name is required"

    @invalid-checkout
    Scenario: Checkout using blank postal code field
      When user add item "Sauce Labs Backpack" to the cart
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with ""
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button
      Then user will see error message "Error: Postal Code is required"

    @valid-checkout
    Scenario: Checkout complete
      When user add item "Sauce Labs Backpack" to the cart
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with ""
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button on checkout information page
      And user will redirect to checkout overview page
      And user click finish button on checkout overview page
      Then user is on the checkout complete page





