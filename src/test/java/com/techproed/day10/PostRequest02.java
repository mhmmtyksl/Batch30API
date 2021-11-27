package com.techproed.day10;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends HerokuAppTestBase {

    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
            {
               "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
            }
            gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
         "booking":
         {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
        olduğunu test edin
     */
    @Test
    public void test() {

        spec02.pathParam("parametre1", "booking");

        // requestBody ve expected Data ayni oldugu icin tek bir JSONObject kullanimi yeterlidir.
        HerokuAppTestData testData=new HerokuAppTestData();
        JSONObject expectedRequestData=testData.setupTestAndRequestData();
        // burada hem expected hem requestimiz ayni degerlere sahip oldugu icin birlikte tanimladik

        // request gonder
        Response response=given().
                contentType(ContentType.JSON).// bu kismi bazen bu sekilde yazmak gerek yoksa internal server hatasi veriyor
                spec(spec02).
                auth().
                basic("admin", "password123").
                body(expectedRequestData.toString()).
                when().
                post("/{parametre1}");

        //response.prettyPrint();

        // De-Serialization yontemi
        HashMap<String , Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap : "+actualDataMap); // burada gormek icin yazdirdik

        Assert.assertEquals(expectedRequestData.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));

        Assert.assertEquals(expectedRequestData.getString("lastname"),
                ((Map)actualDataMap.get("booking")).get("lastname"));

        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                ((Map)actualDataMap.get("booking")).get("totalprice"));

        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                ((Map)actualDataMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map)actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

        // JsonPath yontemi
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals(expectedRequestData.getString("lastname"),
                jsonPath.getString("booking.lastname"));

        Assert.assertEquals(expectedRequestData.getString("firstname"),
                jsonPath.getString("booking.firstname"));

        Assert.assertEquals(expectedRequestData.getInt("totalprice"),
                jsonPath.getInt("booking.totalprice"));

        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"),
                jsonPath.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkin"),
                jsonPath.getString("booking.bookingdates.checkin"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").getString("checkout"),
                jsonPath.getString("booking.bookingdates.checkout"));

    }




}
