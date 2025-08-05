package apiautomation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import static io.restassured.RestAssured.*;

public class testRegres {

    @Test
    public void testGetListUsersValid() {
        File jsonSchema = new File("src/test/resources/JsonSchema/GetListUserSchema.json");

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    public void testGetListUsersInvalid() {
        File jsonSchema = new File("src/test/resources/JsonSchema/GetListUserSchema.json");

        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().all()
                .assertThat().statusCode(201)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
    }

    @Test
    public void testPostCreateUserValid() {
        RestAssured.baseURI = "https://reqres.in/api";

        // Membuat JSON body dengan JSONObject
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("name", "jojo");
        bodyObj.put("job", "Programmer");

        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201);

        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, "jojo");

        System.out.println("Response Body:\n" + response.asPrettyString());
    }

    @Test
    public void testPostCreateUserInvalid() {
        RestAssured.baseURI = "https://reqres.in/api";

        // Membuat JSON body dengan JSONObject
        JSONObject bodyObj = new JSONObject();
        bodyObj.put("name", "jojo");
        bodyObj.put("job", "Programmer");

        Response response = RestAssured
                .given()
                .header("x-api-key", "reqres-free-v1")
                .contentType(ContentType.JSON)
                .body(bodyObj.toString())
                .when()
                .post("/users")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(), 201);

        String name = response.jsonPath().getString("name");
        Assert.assertEquals(name, "jeje");

        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}
