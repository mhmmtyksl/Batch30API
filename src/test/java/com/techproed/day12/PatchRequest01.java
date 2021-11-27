package com.techproed.day12;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PatchRequest01 extends JsonPlaceHolderTestBase {

    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
   {

      "title": "API calismaliyim"

     }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "userId": 10,
 "title": "API calismaliyim"
 "completed": true,
 "id": 198
}
     */
    @Test
    public void test() {

        //url
        spec01.pathParams("parametre1", "todos", "parametre2", 198);

        // expected request data olustur
        JsonPlaceHolderTestData testData=new JsonPlaceHolderTestData();
        JSONObject requestData=testData.setupPatchRequestData();
        System.out.println("requestData : "+requestData);

        JSONObject expectedData=testData.setupPatchExpectedData();
        System.out.println("expectedData : "+ expectedData);

        // request gonder
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(requestData.toString()).when().patch("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //De-serialization
        HashMap<String, Object> actualData=response.as(HashMap.class);
        Assert.assertEquals(expectedData.getInt("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.getInt("id"), actualData.get("id"));
        Assert.assertEquals(expectedData.getString("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.getBoolean("completed"), actualData.get("completed"));



    }

}
