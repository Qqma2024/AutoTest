import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Proxy {
    @Test
    void proxyTest(){
        RestAssured.proxy("127.0.0.1",8888);//全局配置
        //忽略证书检验
//        RestAssured.useRelaxedHTTPSValidation();
        given()
                .header("hello","Hgowarts")
//                .proxy("127.0.0.1",8888) 局部配置
                .relaxedHTTPSValidation()//是https的接口可以 忽略https的校验 也可以忽略证书的校验
        .when()
                .get("https://httpbin.ceshiren.com/get")
         .then()
        .log().all();
    }
}
