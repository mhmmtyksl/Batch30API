package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends DummyTestBase {

    @Test
    public void test() {

        //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde

        spec03.pathParam("parametre1", "employees");

        DummyTestData expectedObje=new DummyTestData();
        HashMap<String, Integer> expectedDataMap= expectedObje.setUpTestData02();
        System.out.println("expectedDataMap : "+ expectedDataMap);

        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        //response.prettyPrint();

        // DE-Serialization islemi yapacagiz
        HashMap<String, Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap : "+actualDataMap);

        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"), (Integer) response.getStatusCode());

        //En yüksek maaşın 725000 olduğunu,

        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),
                actualDataMap.get("data"));
        //En küçük yaşın 19 olduğunu,
        //İkinci en yüksek maaşın 675000
        //olduğunu test edin.





    }



}
