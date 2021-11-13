package odevler;

//      1) Create a class and name it as you wish :)

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C02 {

    @Test
    public void test() {

        //		2) When
        //		     I send a GET Request to https://jsonplaceholder.typicode.com/todos
        String url="https://jsonplaceholder.typicode.com/todos";

        Response response=given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        //		   Then
        //			 HTTP Status code should be "200"
        Assert.assertEquals(200,response.getStatusCode());

        //			 And Content type should be in "JSON" format
        Assert.assertEquals("application/json",response.getContentType());

        //			 And there should be 200 "title"
        JsonPath jsonPath=response.jsonPath();
        System.out.println(jsonPath.getList("title").size());
        Assert.assertEquals(200,jsonPath.getList("title").size());

        //			 And "dignissimos quo nobis earum saepe" should be one of the "title"s
        Assert.assertTrue(jsonPath.getList("title").contains("dignissimos quo nobis earum saepe"));

        //			 And 111, 121, and 131 should be among the "id"s
        Assert.assertTrue(jsonPath.getList("id").contains(111) &&
                                    jsonPath.getList("id").contains(121) &&
                                    jsonPath.getList("id").contains(131));

        //			 And 4th title is "et porro tempora"
        Assert.assertEquals("et porro tempora", jsonPath.getString("title[3]"));

        //			 And last title is "ipsam aperiam voluptates qui"
        Assert.assertEquals("ipsam aperiam voluptates qui", jsonPath.getString("title[-1]"));

    }

}
