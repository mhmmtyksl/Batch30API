package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
// import org.hamcrest.Matchers; normalde ustteki bu sekilde idi * yaparak ve araya static yazarak tum hepsini kolayca import ettik.
// bu sekilde Matchers classin altindaki butun methodlari import etmis olduk
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetRequest03 {
    /*
       https://restful-booker.herokuapp.com/booking/7 url'ine
   accept type'i "application/json" olan GET request'i yolladigimda
   gelen response'un
   status kodunun 200
   ve content type'inin "application/json"
   ve firstname'in "Mary"
   ve lastname'in "Jones"
   ve checkin date'in 2018-10-07"
   ve checkout date'in 2020-09-30 oldugunu test edin
        */
    @Test
    public void test(){
        String url="https://restful-booker.herokuapp.com/booking/7";
        Response response= given().
                accept("application/json").
                when().
                get(url);
        response.prettyPrint();

        /* yogun olarak kullanilan yollardan biri matchers class tir.
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", Matchers.equalTo("Susan")). // anlami firstnama ile Susan biebirine esit mi demektir
                body("lastname",Matchers.equalTo("Wilson")).
                body("totalprice",Matchers.equalTo(903)).
                body("depositpaid",Matchers.equalTo(true)).
                body("bookingdates.checkin",Matchers.equalTo("2021-01-30")). // bu sekilde ic ice olan yapilara komplex Json yapi denir
                body("bookingdates.checkout",Matchers.equalTo("2021-07-30")); //
                */

        // bu veriler anlik degisebilir altta cikani yazinca calisti.

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("firstname", equalTo("Susan"), // normalde bunu yazinca equalTo nun altini cizdi sag tiklayip moreaction dedik
                        // importu tikladik sonra en altta cikan Matchers.equalTo ya tikladik ve import ettik
                        "lastname",equalTo("Wilson"),
                        "totalprice",equalTo(903),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2021-01-30"),
                        "bookingdates.checkout",equalTo("2021-07-30"));


    }
}

// JsonPath
//JsonPath, Json Format ile verilen bir dataya ulasmak veya “manipulate” etmek icin kullanilir.
//$ isareti Json dokumanindaki tum node’lari verir
//Child bolumlere ulasmak icin (.) kullanilabilir. store.book bize tum kitaplari verir
//Belirli bir kitaba ulasmak icin array oldugu icin index kullanabiliriz.
// store.book[1] , Birden fazla kitaba ulasmak istersek virgule indexleri yazabiliriz. store.book[1,3]
//2.kitabin price bilgisine ulasmak icin store.book[1].price kullanabiliriz
//Son kitaba ulasmak icin index olarak -1 kullanabiliriz.
//Tum kitaplarin yazarlarini listelemek icin store.book[*].author kullanabiliriz
//https://jsonpath.herokuapp.com/