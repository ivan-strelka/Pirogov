package com.socks.pages.api.conditions;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {
    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }

    @Step
    public static BodyFieldConditions bodyField(String jsonPath, Matcher matcher) {
        return new BodyFieldConditions(jsonPath, matcher);
    }

}
