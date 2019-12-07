package com.socks.pages.api.test.setting;

import com.socks.pages.api.ProjectConfig;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
public class BaseClass {

    @BeforeAll
    static void setUp() {
        ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
        log.info("HOST IS {}", cfg.host());
        RestAssured.baseURI = cfg.host();
    }

}
