
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
public class CookiesTest {
    @Test
    public void cookiesTest(){
        RestAssured.proxy("127.0.0.1",8888);
        RestAssured.useRelaxedHTTPSValidation();
        given()
//                .header("cookie","12345")
                .cookie("cookie","4567")
        .when()
                .get("https://httpbin.ceshiren.com/get")
        .then()
             .log().all()
        .statusCode(200);
    }

}
