package com.techproed.testData;

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
}
