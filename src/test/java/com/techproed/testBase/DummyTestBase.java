package com.techproed.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyTestBase {

    protected RequestSpecification spec03; // buna 3 yazmamizin anlami kac spec oldugunu bilmek icin yoksa 1 de olabilir

    @Before
    public void setUp() {

        spec03=new RequestSpecBuilder().
                setBaseUri("http://dummy.restapiexample.com/api/v1").
                build();
    }
}
