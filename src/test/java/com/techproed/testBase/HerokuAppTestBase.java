package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerokuAppTestBase {

    protected RequestSpecification spec02; // buna 2 yazmamizin anlami kac spec oldugunu bilmek icin yoksa 1 de olabilir

    @Before
    public void setUp() {

        spec02=new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").
                build();
        // buraya tum elementler icin ayni olan kismi yaziyoruz devamindaki uzantilari parametre
        // olarak diger classta ekleyecegiz cunku.

    }
}

