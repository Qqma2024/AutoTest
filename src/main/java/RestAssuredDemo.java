import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
public class RestAssuredDemo {
    @Test
    public void RestAssuredTest(){
        /**given是放头部信息和参数的
         * when是放请求方式的get，post的
         * then是放断言的
         * **/

        given()
                .param("id",1)
                .log().all()
        .when()
//                .get("http://localhost:8080/pinter/com/getSku?id=1")
                .get("http://localhost:8080/pinter/com/getSku")
        .then()
                .statusCode(200)
        .log().all();

    }

    @Test
    public void PostTest(){
        given()
                .param("userName","admin")
                .param("password","1234")
                .log().all()
                .when()
                .post("http://localhost:8080/pinter/com/login")
                .then()
                .statusCode(200)
                .body("message",equalTo("success"))
                .log().all();

    }


/**
 * post请求json请求体
 * 使用的是String 格式
 * **/
    @Test
    public void PostStringTest(){
        String body="{\"userName\":\"test\",\"password\":\"1234\",\"gender\":1,\"phoneNum\":\"110\",\"email\":\"beihe@163.com\",\"address\":\"Beijing\"}";
        given()
                .contentType("application/json")
                .body(body)
                .log().all()
                .when()
                .post("http://localhost:8080/pinter/com/register")
                .then()
                .statusCode(200)
                .log().all();

    }
    @Test
    public void PostMapTest(){
        //定义入参使用hashMap
        HashMap<String,String> map=new HashMap<>();
        map.put("userName","xiaohong");
        map.put("password","123456");
        map.put("gender","0");
                given()
                  .contentType("application/json")
                  .body(map)
                .when()
                        .post("http://localhost:8080/pinter/com/register")
                .then()
                 .statusCode(200)
                .log().all();

    }

    @Test
    public void PostXmlTest() throws IOException {
        File flie=new File("src/main/resources/add.xml");
        FileInputStream fileInputStream=new FileInputStream(flie);
        String body= IOUtils.toString(fileInputStream,"UTF-8");

        given()
                .contentType("text/xml; charset=utf-8")
                .body(body)
                .log().all()
        .when()
                .post("http://dneonline.com/calculator.asmx")
        .then()
                .statusCode(200)
//                //不管位置，选择所有符合条件的元素
                .body("//AddResult.text()",equalTo("8"))
        .log().all();
    }
}
