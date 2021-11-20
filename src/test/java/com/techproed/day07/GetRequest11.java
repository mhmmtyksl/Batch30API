package com.techproed.day07;

import com.techproed.testBase.JsonPlaceHolderTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest11 extends JsonPlaceHolderTestBase {

    @Test
    public void test() {

        // https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
        // Dönen response un
        // Status kodunun 200, dönen body de,
        //       "completed": değerinin false
        //       "title”: değerinin “quis ut nam facilis et officia qui”
        //       "userId" sinin 1 ve header değerlerinden
        // "Via" değerinin “1.1 vegur” ve
        //       "Server" değerinin “cloudflare” olduğunu test edin…

        spec01.pathParams("parametre1","todos","parametre2", 2);

        HashMap<String, Object> expectedData=new HashMap<String, Object>();
        // burada istenenleri bir map olusturarak icine attik
        // key lerin hepsi String oldugu icin String yazdik ama value ler icinde hem String hem
        // int oldugu icin hepsini kapsamasi icin Object olarak aldik.
        // burada bu sekilde map olusturuyoruz ve tum istenen expected degerleri icine atiyoruz
        // daha sonra assertion yaparken surekli bu map i kullanacagiz
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        System.out.println(expectedData);

        Response response=given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        // 1. yontem Mathers class ile Assertion islemi bu sekilde oluyor
        response.then().assertThat().statusCode((int)expectedData.get("statusCode")).
        headers("via", equalTo(expectedData.get("Via")), // headers in icindekileri burada yazdik
                "Server", equalTo(expectedData.get("Server"))).
        body("userId", equalTo(expectedData.get("userId")), // body nin icindekileri de burada yazdik
                "title", equalTo(expectedData.get("title")),
                "completed", equalTo(expectedData.get("completed")));

        // 2. yontem jsonPath yontemi
        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),response.getStatusCode());// burda headers ta olanlari mecburen response ile aliyoruz
        Assert.assertEquals(expectedData.get("Via"), response.header("via")); // header veya getHeader
        Assert.assertEquals(expectedData.get("Server"), response.header("Server"));
        Assert.assertEquals(expectedData.get("userId"), jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("title"), jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"), jsonPath.getBoolean("completed"));

        // 3. yontem deserialization yontemi
        //   --object mapper
        //   --pojo class ile birlite map
        // bunlari bir sonraki class ta yapmaya devam ettik




    }
}
