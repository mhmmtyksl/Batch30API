package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13JsonPath extends DummyTestBase {

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");// ekstradan tek parametre oldugu icin Param seklinde yazdik

        DummyTestData expectedObje = new DummyTestData();// DummyTestData clasindaki methoda ulasabilmek icin obje olusturduk
        HashMap<String, Object> expectedDataMap = expectedObje.setupTestData();
        System.out.println("expectedDataMap : " + expectedDataMap);

        Response response = given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        //response.prettyPrint();

        // http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
        //Status kodun 200 olduğunu,
        //5. Çalışan isminin "Airi Satou" olduğunu ,
        // çalışan sayısının 24 olduğunu,
        //Sondan 2. çalışanın maaşının 106450 olduğunu
        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
        //11. Çalışan bilgilerinin
        //  {
        // “id”:”11”
        // "employee_name": "Jena Gaines",
        //"employee_salary": "90560",
        //"employee_age": "30",
        //"profile_image": "" }
        //} gibi olduğunu test edin.

        // JsonPath yontemi
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"), jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"), jsonPath.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"), jsonPath.getInt("data[-2].employee_salary"));
        Assert.assertTrue(jsonPath.getList("data.employee_age").containsAll((List) expectedDataMap.get("arananYaslar")));
        Assert.assertEquals(((Map)expectedDataMap.get("onBirinciCalisan")).get("id"),
                jsonPath.getInt("data[10].id"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_name"),
                jsonPath.getString("data[10].employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_salary"),
                jsonPath.getInt("data[10].employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_age"),
                jsonPath.getInt("data[10].employee_age"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("profile_image"),
                jsonPath.getString("data[10].profile_image"));







    }
    }
