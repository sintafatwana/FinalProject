package tests.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import java.io.File;
import java.util.Map;

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
        JSONObject json = new JSONObject(data);
        response = RestAssured
                .given()
                .baseUri("https://reqres.in/api")
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(json.toString())
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract().response();
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
}
