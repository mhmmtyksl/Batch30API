package odevler;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_ {

    /*
     https://restful-booker.herokuapp.com/booking/10 url'ine
     bir GET request gonderdigimizde donen Response'un,
        status code'unun 200,
        ve content type'inin application/json; charset=utf-8,
        ve Server isimli Header'in degerinin Cowboy,
        ve status Line'in HTTP/1.1 200 OK
        ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.
     */

    @Test
    public void test() {

        String url="https://restful-booker.herokuapp.com/booking/10";

        Response response=given().
                when().
                get(url);
        response.prettyPrint();

        // status code'unun 200 oldugunu,
        response.then().assertThat().statusCode(200);

        // content type'inin application/json; charset=utf-8 oldugunu,
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());

        // Server isimli Header'in degerinin Cowboy oldugunu,
        Assert.assertTrue(response.headers().toString().contains("Server=Cowboy"));

        // status Line'in HTTP/1.1 200 OK oldugunu,
        Assert.assertEquals("HTTP/1.1 200 OK", response.statusLine());

        // response suresinin 5 sn'den kisa oldugunu
        Assert.assertTrue(response.time()<5000);

    }

}
