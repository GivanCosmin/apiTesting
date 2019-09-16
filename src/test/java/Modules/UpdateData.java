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

public class UpdateData {

    @Test
    public void updateDAta()
    {
        //Test method used to update data user and validate update data
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1/employees";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET);
        JsonPath jsonPathEvaluator = response.jsonPath();
        List<String> id = jsonPathEvaluator.get("id");
        System.out.println(id.get(0));

        RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
        RequestSpecification request = RestAssured.given();

        //Create jsonobject with attributes and send it to the database + assert to verify output
        JSONObject requestParams = new JSONObject();
        requestParams.put("id",id.get(10));
        requestParams.put("name", "dummyName"); // Cast
        requestParams.put("salary", "dummySalary");
        requestParams.put("age", "dummyAge");

        request.body(requestParams.toJSONString());
        Response response2 = request.put("/update/"+id.get(10));
        System.out.println("Response body: " + response2.body().asString());
        Assert.assertEquals(response2.body().asString(),"{\"name\":\"dummyName\",\"id\":\""+id.get(10)+"\",\"salary\":\"dummySalary\",\"age\":\"dummyAge\"}");

    }
}
