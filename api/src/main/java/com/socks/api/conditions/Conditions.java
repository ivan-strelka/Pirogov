package com.socks.api.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {
    public static StatusCodeCondition statusCode(int statusCode) {
        return new StatusCodeCondition(statusCode);
    }

    public static BodyFieldConditions bodyField(String jsonPath, Matcher matcher) {
        return new BodyFieldConditions(jsonPath, matcher);
    }

}
