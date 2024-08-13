import com.jayway.jsonpath.JsonPath;//使用jayway第三方 json解析
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.*;//使用restassured 自带的json解析


public class TestJsonXPath {

    public String token(){
        String DataToken=given()
                        .contentType("application/x-www-form-urlencoded")
                .when()
                        .post("http://localhost:8080/pinter/bank/api/login2?userName=admin&password=1234")
                .then()
                .statusCode(200)
                .extract().response().asString();//提取响应文本
        System.out.println("token响应"+DataToken);
        return from(DataToken).getString("data");//使用restassured 自带的json解析，截取token字段
    }

    @Test
    public void XPathTest1(){
                String data=given()
                        .contentType("application/x-www-form-urlencoded")
                        .header("testfan-token",token())
                        .log().all()
                .when()
                        .get("http://localhost:8080/pinter/bank/api/query2?userName=admin")
                .then()
                .statusCode(200)
                .extract().response().asString(); //获取响应文本
        String json= JsonPath.read(data,"$.message");//使用jayway第三方 json解析
        System.out.println("//使用jayway第三方 json解析"+json);
        Assert.assertEquals(json,"success");


    }
}
