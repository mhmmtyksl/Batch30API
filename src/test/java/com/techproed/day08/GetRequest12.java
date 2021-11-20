package com.techproed.day08;

import com.techproed.testBase.HerokuAppTestBase;
import com.techproed.testData.HerokuAppTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends HerokuAppTestBase {

    @Test
    public void test() {

        // https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
        // dönen response body nin
        //  {
        //   "firstname": "Eric",
        //   "lastname": "Smith",
        //   "totalprice": 555,
        //   "depositpaid": false,
        //   "bookingdates": {
        //       "checkin": "2016-09-09",
        //       "checkout": "2017-09-21"
        //    }
        //} gibi olduğunu test edin


        spec02.pathParams("parametre1", "booking", "parametre2", 1);

        // simdi expectedData olusturacagiz bunu zaten HerokuAppTestData da olusturduk burada obje uretip cagiracagiz
        HerokuAppTestData expectedObje=new HerokuAppTestData();
        HashMap<String, Object> expectedDataMap=expectedObje.setupTestData();
        // burda TestData clasinda olusturdugumuz map i cagirdik ve baska bir mapin icine attik

        System.out.println("expectedDataMap : "+ expectedDataMap); // bunu sirf gormek icin yazdirdik

        // request gonderiyoruz
        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();// burada response dan donen data bize json formatinda geldi

        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        // burada response dan gelen datayi map e atadik ve yava ya cevirmis olduk
        System.out.println("actualDataMap : "+ actualDataMap);// burda yazdirdigimizda da map olarak yazar

        Assert.assertEquals(expectedDataMap.get("firstname"), actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), actualDataMap.get("depositpaid"));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                // burada expectedDataMap.get("bookingdates") kismini () icine aldik ve bunun icinden checkin datasini getirttik
                // basina da dataCasting yaptik
                ((Map)actualDataMap.get("bookingdates")).get("checkin"));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                ((Map)actualDataMap.get("bookingdates")).get("checkout"));


        // jsonPath ile

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("firstname"), jsonPath.getString("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"), jsonPath.getString("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"), jsonPath.getString("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"), jsonPath.getString("depositpaid"));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals(
                ((Map)expectedDataMap.get("bookingdates")).get("checkout"),
                jsonPath.getString("bookingdates.checkout"));






    }


}
