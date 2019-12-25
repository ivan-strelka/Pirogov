package com.socks.pages.api.conditions;

import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
