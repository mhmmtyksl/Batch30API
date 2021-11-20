package com.techproed.day08;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13 extends DummyTestBase {

    // http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
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

    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");// ekstradan tek parametre oldugu icin Param seklinde yazdik

        DummyTestData expectedObje=new DummyTestData();// DummyTestData clasindaki methoda ulasabilmek icin obje olusturduk
        HashMap<String, Object> expectedDataMap=expectedObje.setupTestData();
        System.out.println("expectedDataMap : "+expectedDataMap);

        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

       // response.prettyPrint();

        // De-Serialization islemi yapacagiz
        HashMap<String , Object> actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap : "+actualDataMap);

        //Status kodun 200 olduğunu,
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        //5. Çalışan isminin "Airi Satou" olduğunu ,
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),
                ((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name"));

        // çalışan sayısının 24 olduğunu,
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List)actualDataMap.get("data")).size());

        //Sondan 2. çalışanın maaşının 106450 olduğunu
        int datasize=((List)actualDataMap.get("data")).size();
                Assert.assertEquals(expectedDataMap.get("sondanIkinciCalisanMaasi"),
                        ((Map)((List)actualDataMap.get("data")).get(datasize-2)).get("employee_salary"));

        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
       List<Integer> actualYasListesi=new ArrayList<Integer>();
       for (int i=0; i<datasize; i++) { // burada actual yaslari bir list e attik ve daha sonra aranan yaslarin
           // olup olmadigini containsAll ile assert ettik
           actualYasListesi.add((int)((Map)((List) actualDataMap.get("data")).get(i)).get("employee_age"));
       }

       Assert.assertTrue(actualYasListesi.containsAll(((List)expectedDataMap.get("arananYaslar"))));


        //11. Çalışan bilgilerinin
        //  {
        // “id”:”11”
        // "employee_name": "Jena Gaines",
        //"employee_salary": "90560",
        //"employee_age": "30",
        //"profile_image": "" }
        //} gibi olduğunu test edin.

        Assert.assertEquals(((Map)expectedDataMap.get("onBirinciCalisan")).get("employee_name"),
                ((Map)((List)actualDataMap.get("data")).get(10)).get("employee_name"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_salary"),
                ((Map)((List<?>) actualDataMap.get("data")).get(10)).get("employee_salary"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("employee_age"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("employee_age"));

        Assert.assertEquals(((Map<?, ?>) expectedDataMap.get("onBirinciCalisan")).get("profile_image"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(10)).get("profile_image"));

    }
}
