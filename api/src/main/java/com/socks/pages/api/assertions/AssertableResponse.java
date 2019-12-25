package com.socks.pages.api.assertions;

import com.socks.pages.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
public class AssertableResponse {

    private final Response response;

    @Step
    public AssertableResponse shouldHave(Condition condition) {
        log.info("About of check coditions {}", condition);
        condition.check(response);
        return this;
    }


}
