package com.techproed.testData;

import java.util.HashMap;

public class HerokuAppTestData {
    //  {
    //   "firstname": "Eric",
    //   "lastname": "Smith",
    //   "totalprice": 555,
    //   "depositpaid": false,
    //   "bookingdates": {
    //       "checkin": "2016-09-09",
    //       "checkout": "2017-09-21"
    //    }
    //}

    public HashMap<String, Object> setupTestData() {
        // return olacagi icin burda da returntype i HashMap yaptik

        HashMap<String, Object> bookingdates=new HashMap<String, Object>();
        bookingdates.put("checkin", "2017-09-01");
        bookingdates.put("checkout", "2021-02-20");

        HashMap<String, Object> expectedData=new  HashMap<String, Object>();
        expectedData.put("firstname", "Mary");
        expectedData.put("lastname", "Jackson");
        expectedData.put("totalprice", 451);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdates);

        return expectedData; // burdaki verileri farkli bir classta kullanacagimiz icin return yapmamiz lazim
        // return un data type da expectedData olmali cunku oyle isimlendirdik bookingdates map i zaten digerinin icinde
        // oldugundan onu dondurmemize gerek yok



    }
}
