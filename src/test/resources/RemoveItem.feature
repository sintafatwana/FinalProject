@RemoveItem
  Feature:
    Background:
      Given user open the login page
      And user login with username "standard_user" and password "secret_sauce"

    Scenario: Remove Item From Cart On Home Page
      When user add item to the cart
      And user click remove button on home page
      Then remove button should change to an add to cart button

    Scenario: Remove Item From Cart On Cart Page
      Given user open login page
      And user login with username and password
      When user add item to the cart
      And user click cart icon
      And user click remove button on cart page
      Then item should disappear on the cart page

    Scenario: Remove Item From Cart On Detail Item Page
      Given user open login page
      And user login with username and password
      When user add item to the cart
      And user click image item on home page
      And user will redirect to detail item page
      And user click remove button on detail item page
      Then the remove button should change to an add to cart button on detail item page

