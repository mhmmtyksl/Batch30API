package com.techproed.day06;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest07 extends HerokuAppTestBase {

    /*
    https://restful-booker.herokuapp.com/booking/2 url’ine bir request yolladigimda
        HTTP Status Code’unun 200
        ve response content type’inin "application/JSON" oldugunu
            ve response body’sinin asagidaki gibi oldugunu test edin
                {"firstname": Sally,
                "lastname": "Smith",
                "totalprice": 789,
                "depositpaid": false,
                "bookingdates": {     "checkin": "2017-12-11",
                                      "checkout":"2020-02-20" }
            }
     */

    // burda jsonPath classini kullaniyoruz bu yuzden Matchers classini kullanmiyoruz.
    // farkeden birsey yok ikisini de kullanabiliriz tamamen istege bagli
    @Test
    public void test01(){
        spec02.pathParams("parametre1","booking",
                "parametre2",2);
        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint(); // burda yazdirmak zorunda degiliz gorelim diye yazdiriyoruz
        // bu kismi dogru yazdigimiz halde hata alip altini kirmizi ciziyorsa
        // Response yanlis kutuphaneden almis olabiliriz bunu gidermek icin
        // Response silip tekrar yaziyoruz ve Response u restassurttan aliyoruz

        JsonPath jsonPath=response.jsonPath(); // jsonPath kullanmak icin oncelikle bunu olusturmaliyiz
        // burada obje uretiyoruz bu sekilde.
        // jsonPath ile sadece body i alabiliriz, headers veya statuscode alamayiz.

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON);
        // Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals("Mark",jsonPath.getString("firstname")); // String oldugu icin getString yazdik
        Assert.assertEquals("Ericsson",jsonPath.getString("lastname"));
        Assert.assertEquals(760,jsonPath.getInt("totalprice")); // int oldugu icin getInt olarak aldik
        Assert.assertEquals(true,jsonPath.getBoolean("depositpaid"));// boolean oldugu icin getBoolean yazdik
        // Assert.assertTrue(jsonPath.getBoolean("depositpaid")); // bu sekilde de yazilabilir hatta ustteki gibi yazinca altini fosforlu yapiyor
        // anlami true veya fase seklinde yaz demek
        Assert.assertEquals("2017-04-22",jsonPath.getString("bookingdates.checkin")); // bookingdates in altindaki checkin demek
        Assert.assertEquals("2017-10-19",jsonPath.getString("bookingdates.checkout"));
    }
}