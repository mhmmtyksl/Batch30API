package com.techproed.testData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
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

    public HashMap<String, Object> setupTestData() {

        // burada verilen yaslara dair  datalari bir List e attik
        List<Integer> yaslar=new ArrayList<Integer>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        // burada 11. calisana dair verilen datalari bir Map e attik
        HashMap<String, Object> onbirinci=new HashMap<String, Object>();
        onbirinci.put("id", 11);
        onbirinci.put("employee_name", "Jena Gaines");
        onbirinci.put("employee_salary", 90560);
        onbirinci.put("employee_age", 30);
        onbirinci.put("profile_image", "");

        // burada verilen datalari ve diger olusturdugumuz Map i ve List i genel bir Map e attik
        HashMap<String, Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("besinciCalisan", "Airi Satou");
        expectedData.put("calisanSayisi", 24);
        expectedData.put("sondanIkinciCalisanMaasi", 106450);
        expectedData.put("arananYaslar", yaslar);
        expectedData.put("onBirinciCalisan", onbirinci);

        return expectedData;

    }

    //http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    //Status kodun 200 olduğunu,
    //En yüksek maaşın 725000 olduğunu,
    //En küçük yaşın 19 olduğunu,
    //İkinci en yüksek maaşın 675000
    //olduğunu test edin.
    public HashMap<String,Integer> setUpTestData02(){
        HashMap<String,Integer> expectedDataMap=new HashMap<String, Integer>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("enYuksekMaas",725000);
        expectedDataMap.put("enKucukYas",19);
        expectedDataMap.put("ikinciEnYuksekMaas",675000);
        return expectedDataMap;
    }

    public HashMap<String, String> setupRequestBody() {

        HashMap<String, String> requestBody=new HashMap<String, String>();
        requestBody.put("name", "Ahmet");
        requestBody.put("salary", "5000");
        requestBody.put("age", "34");
        return requestBody;
    }

    public HashMap<String, Object> setupExpectedData() {

        /*
        HashMap<String, Object> data=new HashMap<String, Object>();
        data.put("name", "Ahmet");
        data.put("salary", "5000");
        data.put("age", "34");
        */

        HashMap<String ,Object> expectedData=new HashMap<String, Object>();
        expectedData.put("statusCode", 200);
        expectedData.put("status", "success");
       // expectedData.put("data", data);
        expectedData.put("message", "Successfully! Record has been added.");

        return expectedData;
    }

}
