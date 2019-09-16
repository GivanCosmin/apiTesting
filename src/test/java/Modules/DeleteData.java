package Modules;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class DeleteData {
    @Test
    public void deleteData()
    {
        //Test method used to delete user and validate his deletion in the database
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> id = jsonPathEvaluator.get("id");
        System.out.println(id.get(10));

        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
        RequestSpecification request = RestAssured.given();

        //Validate that the user was sucessfully deleted
        Response response2 = request.delete("/delete/"+id.get(10));
        System.out.println("Response body: " + response2.body().asString());
        Boolean deleteVerification = response2.body().asString().contains(id.get(10));
        Assert.assertFalse(deleteVerification,"Id: " + id.get(10) + "was sucessfully deleted");

    }
}
