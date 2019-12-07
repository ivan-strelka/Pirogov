package com.socks.pages.api.sevices;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiServices {

    public RequestSpecification settings() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }


}
