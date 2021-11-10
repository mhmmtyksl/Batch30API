package com.techproed.day05;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*; // burada yine hasSize i silip * koyduk ki hepsini import etmis olalim

public class GetRequest05 {
    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200                                                                                        ve content type'inin "application/json"
   ve employees sayisinin 24
   ve employee'lerden birinin "Ashton Cox"
   ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
        */

    @Test
    public void test() {
        String url = "http://dummy.restapiexample.com/api/v1/employees";
        Response response = given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.profile_image",hasSize(24), // burada istersek id yi istersek diger herhangi birini saydirabiliriz
                "data.employee_name",hasItem("Ashton Cox"), // hasItem boyle bir degere sahip mi anlaminda contains gibi
                "data.employee_age",hasItems(21,61,23)); // birden fazla degeri kontrol edeceksek bu sekilde , ile hepsini yazabiliriz

// normalde pass oldu test bazen fazla calistirmadan dolayi hata verebilir tekrar calistirmak gerekli dummy de olabiliyor bu







    }
}