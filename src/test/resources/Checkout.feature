@Checkout
  Feature: Checkout Item

    @invalid-checkout
    Scenario: Checkout using blank first name field
      Given user open login page
      And user login with username and password
      And user will redirect to homepage
      And user add item to the cart
      And user click cart icon
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with ""
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button on checkout information page
      Then user will see error message "Error: First Name is required"

    @invalid-checkout
    Scenario: Checkout using blank last name field
      Given user open login page
      And user login with username and password
      And user will redirect to homepage
      And user add item to the cart
      And user click cart icon
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with "sinta"
      And user input last name field with ""
      And user input postal code field with "60234"
      And user click continue button on checkout information page
      Then user will see error message "Error: Last Name is required"

    @invalid-checkout
    Scenario: Checkout using blank postal code field
      Given user open login page
      And user login with username and password
      And user will redirect to homepage
      And user add item to the cart
      And user click cart icon
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with "sinta"
      And user input last name field with "fatwana"
      And user input postal code field with ""
      And user click continue button on checkout information page
      Then user will see error message "Error: Postal Code is required"

    @valid-checkout
    Scenario: Checkout complete
      Given user open login page
      And user login with username and password
      And user will redirect to homepage
      And user add item to the cart
      And user click cart icon
      And user will redirect to cart page
      And user click checkout button on cart page
      And user will redirect to checkout information page
      And user input first name field with "sinta"
      And user input last name field with "fatwana"
      And user input postal code field with "60234"
      And user click continue button on checkout information page
      And user will redirect to checkout overview page
      And user click finish button on checkout overview page
      Then user is on the checkout complete page





