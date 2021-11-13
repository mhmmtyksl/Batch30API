package com.techproed.day06;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends DummyTestBase {

    // http://dummy.restapiexample.com/api/v1/employees
    // url ine bir istek gönderildiğinde,
    // status kodun 200,
    // gelen body de,
    // 5. çalışanın isminin "Airi Satou" olduğunu ,
    // 6. çalışanın maaşının "372000" olduğunu ,
    // Toplam 24 tane çalışan olduğunu,
    // "Rhona Davidson" ın employee lerden biri olduğunu
    // "21", "23", "61" yaşlarında employeeler olduğunu test edin





    @Test
    public void test() {

        spec03.pathParam("parametre1", "employees");

        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        // status kodun 200,
        Assert.assertEquals(200, response.getStatusCode());
        //Assert.assertTrue(response.getStatusCode()==200);

        // gelen body de,
        // 5. çalışanın isminin "Airi Satou" olduğunu ,
        Assert.assertEquals("Airi Satou", jsonPath.getString("data[4].employee_name"));

        // 6. çalışanın maaşının "372000" olduğunu ,
        Assert.assertEquals(372000, jsonPath.getInt("data[5].employee_salary"));

        // Toplam 24 tane çalışan olduğunu,
        System.out.println(jsonPath.getList("data.id").size());
        Assert.assertEquals(24, jsonPath.getList("data.id").size());

        // "Rhona Davidson" ın employee lerden biri olduğunu
        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        // "21", "23", "61" yaşlarında employeeler olduğunu test edin
        Assert.assertTrue(jsonPath.getList("data.employee_age").contains(21) ||
                jsonPath.getList("data.employee_age").contains(23) ||
                jsonPath.getList("data.employee_age").contains(61));

    }
}
