package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    /*
    https://restful-booker.herokuapp.com/booking/3 adresine
    bir request gonderildiginde donecek cevap(response) icin
    HTTP status kodunun 200
    Content Type'in Json
    Ve Status Line'in HTTP/1.1 200 OK
    Oldugunu test edin
     */

    @Test
    public void test01() {

    // 1) API testi yaparken ilk olarak Url (endpoint) belirlenmeli
    String url="https://restful-booker.herokuapp.com/booking/3";

    // 2) Beklenen sonuc(expected result) olusturulur
    //    Bu case de bizden body dogrulemesi istenmedigi icin simdilik beklenen sonuc olusturmuyoruz

    // 3) Request gonder
        Response response=given(). // okunabilirligi arttirmak adina bu sekilde yazilir genelde noktadan sonra alta
            accept("application/json").
            when().
            get(url);

        response.prettyPrint(); // burada yazdiriyoruz yazdirma komutu

    // 4) actual result olustur.
        // response body ile ilgili bir islem yapmayacagimiz icin simdi olusturmayacagiz

    // 5) dogrulama yap(assertion)
        System.out.println("status code : "+ response.getStatusCode()); // response dan gelen status code u verir
        System.out.println("content type : "+response.getContentType()); // response dan gelen content type verir
        System.out.println("status line : "+response.getStatusLine()); // response dan gelen status line i verir
        System.out.println("response.getHeaders() = " + response.getHeaders()); // bununla postmanda headers ta gorunenlerin hepsini getirir

        /*
        Assert.assertEquals(200,response.getStatusCode());
        // expected kismi bize task olarak verilen degerdir, actual kismi
        // ise response dan donen degerdir

        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());

        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
        */

        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON). // buraya string olarak "application/json" yazilabilir ama bazen hata verebilir.
                statusLine("HTTP/1.1 200 OK");
        // bu sekilde ustte yoruma aldigimiz assertionlarin hepsini tek seferde yapmis oluruz.

    }
}
