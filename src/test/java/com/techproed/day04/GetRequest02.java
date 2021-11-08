package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class GetRequest02 {

    /*
   https://restful-booker.herokuapp.com/booking url'ine
   accept type'i "application/json" olan GET request'i yolladigimda
   gelen response'un
   status kodunun 200
   content type'inin "application/json" oldugunu test edin

    */
    @Test
    public void test1() {

        // url olustur
        String url="https://restful-booker.herokuapp.com/booking";

        // request gonder
        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        // dogrulama yap
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON);












    }
    @Test
    public void test2() {

        /*
       https://restful-booker.herokuapp.com/booking/1001 url'ine
        accept type'i "application/json" olan GET request'i yolladigimda
        gelen response'un
        status kodunun 404 oldugunu,
        Response body'sinin "Not Found" icerdigini,
        ve Response body'sinin "API" icermedigini test edin
     */

        String url="https://restful-booker.herokuapp.com/booking/1001";

        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(404);

        Assert.assertTrue(response.asString().contains("Not Found"));
        // asString methodu ile json formatinda gelen response u stringe cevirdik

        Assert.assertFalse(response.asString().contains("API"));



    }
}
