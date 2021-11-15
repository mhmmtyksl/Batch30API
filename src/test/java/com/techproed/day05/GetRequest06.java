package com.techproed.day05;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest06 extends JsonPlaceHolderTestBase {

    /*
    https://jsonplaceholder.typicode.com/todos/123 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response’un
    status kodunun 200
    ve content type'inin "application/json"
    ve Headers'daki "Server" in "cloudflare"
    ve response body'deki "userId"'nin 7
    ve "title" in "esse et quis iste est earum aut impedit"
    ve "completed" bolumunun false oldugunu test edin
     */

    @Test
    public void test() {

        // String url="https://jsonplaceholder.typicode.com/todos/123";
        spec01.pathParams("parametre1", "todos",
                "parametre2", 123);
        // burada parametre1 ve 2 isimleri tamamen keyfi, burada parametre1 todos kismini, parametre2 123 u ifade eder
        // burada degerlerini atamis olduk gitmek istedigimiz adrese gore
        // key/value formetinda yaziyoruz

        Response response=given().
                accept("application/json").
                spec(spec01). // burada testBase deki belirlenen adresi aldik ve devaminda alttakileri ekledik
                when().
                get("/{parametre1}/{parametre2}");
        // buradaki / ler adrestekilerin yerine, ustteki todos ve 123 u bu sekilde aliyoruz

        response.prettyPrint();

        // buraya kadar herhangi bir assert yazmadan da kodu calistirsak pass der bunun anlami buraya kadar yazilan kodlarda hata yoktur demektir.



        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server",equalTo("cloudflare")).
                body("userId",equalTo(7),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed",equalTo(false));







    }

}
