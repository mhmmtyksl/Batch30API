package com.techproed.day10;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends DummyTestBase {
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */
    @Test
    public void test() {
        spec03.pathParam("parametre1", "create");

        DummyTestData obje=new DummyTestData();

        //postRequest yaparken body gondermeliyiz. tesdData class inda olusturdugumuz request Body i
        // burada cagiriyoruz.
        HashMap<String, String> requestBodyMap=obje.setupRequestBody();
        HashMap<String, Object> expectedDataMap=obje.setupExpectedData();

        Response response=given().
                accept("application/json").
                spec(spec03).auth().basic("admin", "password123").
                body(requestBodyMap).
                when().
                post("/{parametre1}");

        response.prettyPrint();

        // De-serialization
        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));

        // jsonPath yontemi
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedDataMap.get("status"), jsonPath.getString("status"));
        Assert.assertEquals(expectedDataMap.get("message"), jsonPath.getString("message"));

    }

}
