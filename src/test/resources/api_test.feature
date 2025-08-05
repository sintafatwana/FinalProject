@apiTest
  Feature: Reqres API Tests
    Scenario: GET List Users - Valid Response
      When I send GET request to "https://reqres.in/api/users?page=2"
      Then the response status code should be 200
      And the response should match JSON schema "GetListUserSchema.json"

    Scenario: GET List Users - Invalid Status Code
      When I send GET request to "https://reqres.in/api/users?page=2"
      Then the response status code should be 201
      And the response should match JSON schema "GetListUserSchema.json"

    Scenario: POST Create User - Valid Data
      When I send POST request to "/users" with body:
        | name | jojo         |
        | job  | Programmer   |
      Then the response status code should be 201
      And the response body "name" should be "jojo"

    Scenario: POST Create User - Invalid Name Check
      When I send POST request to "/users" with body:
        | name | jojo         |
        | job  | Programmer   |
      Then the response status code should be 201
      And the response body "name" should be "jeje"
