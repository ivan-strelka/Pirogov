package com.socks.api.sevices;

import com.socks.api.assertions.AssertableResponse;
import com.socks.api.payload.PayLoadUserRegistration;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserApiServices extends ApiServices {
    @Step
    public AssertableResponse registerNewUser(PayLoadUserRegistration payLoadUserRegistration) {
        log.info("Info about of create a new user{}", payLoadUserRegistration);
        return new AssertableResponse(settings()
                .body(payLoadUserRegistration)
                .when()
                .post("/register"));

    }


}
