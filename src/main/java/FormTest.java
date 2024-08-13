
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;


public class FormTest {
    @Test
    void test(){
        RestAssured.proxy("127.0.0.1",8888);
        RestAssured.useRelaxedHTTPSValidation();
        given()
                .auth().basic("test","1234")
                .cookie("testfan-id","45e44937-bcf8-4a44-92d0-829501a6fbed")
//                .formParam("userName","admin")
//                .formParam("password","1234")
                .formParams("userName","admin","password","1234")//表单请求会默认application/x-www-form-urlencoded;
        .when()
                .post("http://localhost:8080/pinter/bank/api/login")
        .then().log().all()
        .statusCode(200)
        .time(lessThan(2000L));
    }
}
