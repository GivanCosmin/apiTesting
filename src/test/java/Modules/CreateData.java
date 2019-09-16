package Modules;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class CreateData {

    @Test
    public void createData()
    {
        //Test method used to create user and validate his creation in the database
        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "test_gk_unique"); // Cast
        requestParams.put("salary", "123");
        requestParams.put("age", "23");

        //Send the params to Json and assert to validate the new entry
        request.body(requestParams.toJSONString());
        Response response = request.post("/create");
        System.out.println("Response body: " + response.body().asString());
        Assert.assertEquals(response.body().asString(),"\"name\":\"test_gk_unique\",\"salary\":\"123\",\"age\":\"23\"");

    }
}
