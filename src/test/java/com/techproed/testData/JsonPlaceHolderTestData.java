package com.techproed.testData;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String, Object> setupTestData() {
        // burada method olusturuyoruz ve bu method bize bir Map dondurecek

        // burada test edecegimiz testDatalarini bunun icine yazdik
    HashMap<String, Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("Via", "1.1 vegur");
        expectedData.put("Server", "cloudflare");
        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);

        return expectedData;
    }

    public JSONObject setupPostTestData() {

        //      "userId": 55,
        //     "title": "Tidy your room",
        //     "completed": false
        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("statusCode", 201);
        expectedRequest.put("userId", 55);
        expectedRequest.put("title", "Tidy your room");
        expectedRequest.put("completed", false);

        return expectedRequest;
    }

    public JSONObject setupPutTestData() {
        //      "userId": 21,
        //      "title": "Wash the dishes",
        //      "completed": false

        JSONObject expectedRequest=new JSONObject();
        expectedRequest.put("userId", 21);
        expectedRequest.put("title", "Wash the dishes");
        expectedRequest.put("completed", false);

        return expectedRequest;
    }




}
