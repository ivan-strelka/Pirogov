package com.socks.api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusCodeCondition implements Condition {

    private final int expectedStatusCode;

    public void check(Response response) {
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Override
    public String toString() {
        return "Status code is " + expectedStatusCode;
    }
}
