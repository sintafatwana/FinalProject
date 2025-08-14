@api
  Feature: Reqres API Tests
    Scenario: GET List Users - Valid Response
      When I send GET request to "https://reqres.in/api/users?page=2"
      Then the response status code should be 200
      And the response should match JSON schema "GetListUserSchema.json"

    Scenario: GET List Users - Wrong Data Type
      When I send GET request to "https://reqres.in/api/users?page=2"
      Then the response status code should be 200
      And the response should mismatch JSON schema "GetListUserSchema2.json"

    Scenario: GET List Users - Page Number As Negative
      When I send GET request to "https://reqres.in/api/users?page=-2"
      Then the response status code should be 401

    Scenario: GET List Users - Page Number As Special Character
      When I send GET request to "https://reqres.in/api/users?page=@@@"
      Then the response status code should be 401

    Scenario: GET List Users - Page Parameter Missing
      When I send GET request to "https://reqres.in/api/users"
      Then the response status code should be 200

    Scenario: GET List Users - Wrong Parameter Name
      When I send GET request to "https://reqres.in/api/users?pg=2"
      Then the response status code should be 401

    Scenario: GET List Users - Page Number Too Large
      When I send GET request to "https://reqres.in/api/users?page=999"
      Then the response status code should be 401

    Scenario: GET List Users - Use Method PUT
      When I send GET request to "https://reqres.in/api/users?page=2" use method "PUT"
      Then the response status code should be 401

    Scenario: GET List Users - Invalid Status Code
      When I send GET request to "https://reqres.in/api/users?page=2"
      Then the response status code should be 201

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

    Scenario: POST Create User - Missing Name
      When I send POST request to "/users" with body:
        |job|Programmer|
      Then the response status code should be 201
      And the response body "job" should be "Programmer"

    Scenario: POST Create User - Missing Job
      When I send POST request to "/users" with body:
        |name|bella|
      Then the response status code should be 201
      And the response body "name" should be "bella"

    Scenario: POST Create User - Empty JSON Body
      When I send POST request to "/users" with body:
      Then the response status code should be 201

    Scenario: POST Create User - Job As Null
      When I send POST request to "/users" with body:
        |job|null|
        |name|fitria|
      Then the response status code should be 201
      And the response body "job" should be not null

    Scenario: POST Create User - Name As Number
      When I send POST request to "/users" with body:
        |job|QA|
        |name|12345|
      Then the response status code should be 201
      And the response body "name" should be "12345"

    Scenario: POST Create User - Extra Field
      When I send POST request to "/users" with body:
        |job|QA|
        |name|fitria|
        |age |25    |
      Then the response status code should be 201

