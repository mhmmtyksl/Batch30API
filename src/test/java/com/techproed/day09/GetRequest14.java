package com.techproed.day09;

import com.techproed.testBase.DummyTestBase;
import com.techproed.testData.DummyTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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
        List<Integer> maasListesi=new ArrayList<Integer>();
        int dataSize=((List)actualDataMap.get("data")).size();
        for (int i=0; i<dataSize; i++) {
            maasListesi.add((Integer)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary"));
        }
        Collections.sort(maasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),maasListesi.get(maasListesi.size()-1));

        //En küçük yaşın 19 olduğunu,
        List<Integer> yasListesi=new ArrayList<Integer>();
        int dataSizeYas=((List)actualDataMap.get("data")).size();
        for (int i=0; i<dataSizeYas; i++) {
            yasListesi.add((Integer)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        Collections.sort(yasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),yasListesi.get(0));

        //İkinci en yüksek maaşın 675000
        //olduğunu test edin.
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesi.get(maasListesi.size()-2));

        // Json Path yontemi

        JsonPath jsonPath=response.jsonPath();

        //En yüksek maaşın 725000 olduğunu,
        List<Integer> maasListesiJson=jsonPath.getList("data.employee_salary");
        Collections.sort(maasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), maasListesiJson.get(maasListesiJson.size()-1));

        //En küçük yaşın 19 olduğunu,
        List<Integer> yasListesiJson=jsonPath.getList("data.employee_age");
        Collections.sort(yasListesiJson);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), yasListesiJson.get(0));

        //İkinci en yüksek maaşın 675000
        //olduğunu test edin.
        Assert.assertEquals(expectedDataMap.get("ikinciEnYuksekMaas"), maasListesiJson.get(maasListesiJson.size()-2));



    }

}
