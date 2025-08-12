package tests.stepdefs;

import groovy.lang.GString;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.File;
import java.util.Map;

import static org.junit.Assert.fail;

public class RegresStepDef {
    private Response response;

    // ✅ Step GET request
    @When("I send GET request to {string}")
    public void iSendGETRequestTo(String url) {
        response = RestAssured
                .given()
                .when()
                .get(url)
                .then()
                .log().all()
                .extract().response();
    }

    // ✅ Step POST request with body
    @When("I send POST request to {string} with body:")
    public void iSendPOSTRequestToWithBody(String endpoint, Map<String, String> data) {
        JSONObject jsonObject = new JSONObject(data);
        response = RestAssured
                .given()
                .baseUri("https://reqres.in/api")
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(jsonObject.toString())
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();

        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            // Jika value "null", ubah jadi JSONObject.NULL (Java null untuk JSON)
            if ("null".equalsIgnoreCase(value)) {
                jsonObject.put(key, JSONObject.NULL);
            } else {
                jsonObject.put(key, value);
            }
        }
    }

    // ✅ Step status code assertion
    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    // ✅ Step schema validation
    @And("the response should match JSON schema {string}")
    public void theResponseShouldMatchJSONSchema(String schemaFileName) {
        File schema = new File("src/test/resources/JsonSchema/" + schemaFileName);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    // ✅ Step to validate a JSON value
    @And("the response body {string} should be {string}")
    public void theResponseBodyShouldBe(String jsonKey, String expectedValue) {
        String actualValue = response.jsonPath().getString(jsonKey);
        Assert.assertEquals(expectedValue, actualValue);
    }

    @When("I send GET request to {string} use method {string}")
    public void iSendGETRequestToUseMethod(String url, String method) {
        RequestSpecification requestSpecification = RestAssured.given();

        switch (method.toUpperCase()){
            case "PUT":
                response = requestSpecification.when().put(url);
                break;
            case "POST":
                response = requestSpecification.when().post(url);
                break;
            default:
                throw new IllegalArgumentException ("Unsupported method: "+method);
        }
        this.response = response.then().log().all().extract().response();
    }

    @And("the response body {string} should be not null")
    public void theResponseBodyShouldBeNotNull(String fieldName) {
        String value = response.jsonPath().getString(fieldName);
        Assert.assertNotNull(value, "field "+fieldName+ "is null. It should NOT be null.");
    }

    @And("the response should mismatch JSON schema {string}")
    public void theResponseShouldMismatchJSONSchema(String schemaFileName) {
        File schema = new File("src/test/resources/JsonSchema/" + schemaFileName);
        try {
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
            fail("Response matched the schema but it should not.");
        } catch (AssertionError e) {
            // Expected: schema mismatch
            System.out.println("Schema validation failed as expected: " + e.getMessage());
        }
    }
}
