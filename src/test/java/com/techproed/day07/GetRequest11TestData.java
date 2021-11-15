package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import com.techproed.testData.JsonPlaceHolderTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest11TestData extends JsonPlaceHolderTestBase {
    @Test
    public void test() {

        // https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
        // Dönen response un
        // Status kodunun 200, dönen body de,
        //       "completed": değerinin false
        //       "title”: değerinin “quis ut nam facilis et officia qui”
        //       "userId" sinin 1 ve header değerlerinden
        // "Via" değerinin “1.1 vegur” ve
        //       "Server" değerinin “cloudflare” olduğunu test edin…

        spec01.pathParams("parametre1","todos","parametre2", 2);

        JsonPlaceHolderTestData expectedObje=new JsonPlaceHolderTestData();

        HashMap<String, Object> expectedData= (HashMap<String, Object>) expectedObje.setupTestData();
        System.out.println(expectedData);

        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        // 1. yontem Mathers class ile Assertion islemi bu sekilde oluyor
        response.then().assertThat().statusCode((int)expectedData.get("statusCode")).
                headers("via", equalTo(expectedData.get("Via")),
                        "Server", equalTo(expectedData.get("Server"))).
                body("userId", equalTo(expectedData.get("userId")),
                        "title", equalTo(expectedData.get("title")),
                        "completed", equalTo(expectedData.get("completed")));

        // 2. yontem jsonPath yontemi
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedData.get("Via"), response.header("via")); // header veya getHeader
        Assert.assertEquals(expectedData.get("Server"), response.header("Server"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

        // 3. yontem deserialization yontemi
        //   --object mapper
        //   --pojo class ile birlite map




    }
}
