package com.techproed.day07;

import com.techproed.testBase.DummyTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends DummyTestBase {

    @Test
    public void test() {

        // http://dummy.restapiexample.com/api/v1/employees
        // url ine bir istek gönderildiğinde
        // Dönen response un
        // Status kodunun 200,
        // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
        // 10’dan büyük 14 id olduğunu,
        // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu
        // 3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //  bunların içerisinde “Charde Marshall” olduğunu test edin

        spec03.pathParam("parametre1", "employees");
        Response response=given().
                accept("application/json").
                spec(spec03).
                when().
                get("/{parametre1}");

        response.prettyPrint();

        // Dönen response un
        // Status kodunun 200,
        Assert.assertEquals(200,response.getStatusCode());

        JsonPath jsonPath=response.jsonPath();
        // 1)10’dan büyük tüm id’leri ekrana yazdırın ve
        List<Integer> idList= jsonPath.getList("data.findAll{it.id>10}.id"); // bu sekilde yazim Groovy dilidir.
        // buradaki it yapidan gelen birseydir.yukaridaki yazim bize bir List dondurur bu yuzden List e atadik
        System.out.println(idList);

        // 10’dan büyük 14 id olduğunu,
        Assert.assertEquals(14,idList.size());

        // 2)30’dan küçük tüm yaşları ekrana yazdırın ve
        List<Integer> yasListesi=jsonPath.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(yasListesi);

        //  bu yaşların içerisinde en büyük yaşın 23 olduğunu
        Collections.sort(yasListesi);// burada kucukten buyuge siraladik ve son index tekini alarak en buyugunu bulduk
        Assert.assertEquals((Integer)23,yasListesi.get(yasListesi.size()-1));
        // data typlari farkli oldugu icin hata verdi 23 un basina (Integer) yazarak dataCasting islemi yaptik
        // ya 23 un basina (Integer) yazariz yada ikinci siradakinin basina (int) yazarak birbirine uygun hale getiririz
        // Assert.assertEquals(23,(int)yasListesi.get(yasListesi.size()-1)); // bu sekilde de olabilir

        // 3)Maası 350000 den büyük olan tüm employee_name’leri ekrana yazdırın ve
        List<String> isimListesi=jsonPath.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(isimListesi); // burda yas sartina bagli olarak isimleri getirttik
        // yani sadece sarta bagladiklarimizi yazdirmak veya bulmak zorunda degiliz, farkli degerleri de alabiliriz

        //  bunların içerisinde “Charde Marshall” olduğunu test edin
        Assert.assertTrue(isimListesi.contains("Charde Marshall"));


        // Groovy dili javanin alt dilidir. biz bu dil yardimiyla loop kullanmadan gelen
        // response daki degerleri bir sarta bagli olarak liste ye yazdirabiliriz.






    }
}
