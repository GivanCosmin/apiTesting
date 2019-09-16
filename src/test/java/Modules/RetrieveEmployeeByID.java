package Modules;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import java.util.List;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class RetrieveEmployeeByID {

    @Test
    public void retrieveEmployeeAndValidateSchema()
    {

        //Test method used to retrieve first user data and validate schema
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";

        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> id = jsonPathEvaluator.get("id");
        System.out.println(id.get(0));

        //Validate that schema for single user retrieval
        RestAssured
                .given()
                .when()
                .get("http://dummy.restapiexample.com/api/v1/employee/"+id.get(0))
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("employee_schema.json"));

    }

}

