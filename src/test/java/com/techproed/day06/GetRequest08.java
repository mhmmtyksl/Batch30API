package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends DummyTestBase {

    // http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
    //   1) Butun calisanlarin isimlerini consola yazdıralim
    //   2) 3. calisan kisinin ismini konsola yazdıralim
    //   3) Ilk 5 calisanin adini konsola yazdiralim
    //   4) En son calisanin adini konsola yazdiralim

    @Test
    public void test() {

        spec03.pathParam("parametre", "employees");

        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        //   1) Butun calisanlarin isimlerini consola yazdıralim
        System.out.println(jsonPath.getList("data.employee_name"));
        // System.out.println(jsonPath.getString("data.employee_name")); // bu sekilde d etum isimleri yazdirdi

        //   2) 3. calisan kisinin ismini konsola yazdıralim
        System.out.println(jsonPath.getString("data[2].employee_name")); // 3. kisi icin 2. index yaziyoruz
        // System.out.println(jsonPath.getString("data.employee_name[2]")); // bu sekilde de yazar ama ustteki daha dogru kullanis

        //   3) Ilk 5 calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data[0,1,2,3,4].employee_name"));

        //   4) En son calisanin adini konsola yazdiralim
        System.out.println(jsonPath.getString("data[-1].employee_name"));

        // simdi de Assertlerini yapalim expectedlari asagidan konsoldan aliyoruz.
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Ashton Cox", jsonPath.getString("data[2].employee_name"));
        Assert.assertEquals("Doris Wilder", jsonPath.getString("data[-1].employee_name"));


    }

}
