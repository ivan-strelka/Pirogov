import com.socks.api.payload.PayLoadUserRegistration;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
public class UserRegistration {
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

        registerNewUser(payLoadUser)
                .then()
                .body("id", Matchers.not(Matchers.isEmptyString()));
    }

    @Test
    void canNotRegisterWithoutName() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(0));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        registerNewUser(payLoadUser)
                .then()
                .statusCode(500);
    }

    @Test
    void canRegisterWithSpaceInEmail() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + " @gmail.com");

        registerNewUser(payLoadUser)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.isEmptyString()));

    }

    @Test
    void canRegisterWithoutPassword() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(0));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(10) + "@gmail.com");

        registerNewUser(payLoadUser)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.isEmptyString()));
    }

    @Test
    void canRegisterWithoutEmail() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(9));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(0));

        registerNewUser(payLoadUser)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.isEmptyString()));
    }

    @Test
    void canNotRegisterTwice() {
        PayLoadUserRegistration payLoadUser = new PayLoadUserRegistration();
        payLoadUser.setUsername(RandomStringUtils.randomAlphanumeric(5));
        payLoadUser.setPassword(RandomStringUtils.randomAlphanumeric(9));
        payLoadUser.setEmail(RandomStringUtils.randomAlphabetic(6) + "@gmail.com");

        registerNewUser(payLoadUser)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.isEmptyString()));

        registerNewUser(payLoadUser)
                .then()
                .statusCode(500);

    }

    private RequestSpecification settings() {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .accept(ContentType.TEXT)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    private Response registerNewUser(PayLoadUserRegistration payLoadUser) {
        return settings()
                .body(payLoadUser)
                .when()
                .post("/register");
    }

}
