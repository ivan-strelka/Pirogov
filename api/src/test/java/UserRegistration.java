import com.socks.api.conditions.Conditions;
import com.socks.api.conditions.StatusCodeCondition;
import com.socks.api.payload.PayLoadUserRegistration;
import com.socks.api.sevices.UserApiServices;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;

@Slf4j
public class UserRegistration {

    private final UserApiServices userApiServices = new UserApiServices();

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://157.245.169.246";
    }


    @Test
    void canRegisterWithAllParam() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(8));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(Conditions.statusCode(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));
    }

    @Test
    void canNotRegisterWithoutName() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(0));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(500));
    }

    @Test
    void canRegisterWithSpaceInEmail() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + " @gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));

    }

    @Test
    void canRegisterWithoutPassword() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(0));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));

    }

    @Test
    void canRegisterWithoutEmail() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(9));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(0));

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));

    }

    @Test
    void canNotRegisterTwice() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(9));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(6) + "@gmail.com");

        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(200))
                .shouldHave(Conditions.bodyField("id", not(isEmptyString())));


        userApiServices.registerNewUser(payLoadUser)
                .shouldHave(new StatusCodeCondition(500));

    }


}
