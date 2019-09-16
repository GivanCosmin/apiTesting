package Modules;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RetrieveAllEmployees {

    @Test
    public void retrieveAllEmployessAndValidateSchema()
    {
        //Test method used to retrieve all data and validate schema
        RestAssured
                .given()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employees")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("employees_schema.json"));

    }

}

