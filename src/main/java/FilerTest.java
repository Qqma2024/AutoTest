
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Base64;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;

public class FilerTest {

    @BeforeClass //全局的
    public void filterTest(){
        useRelaxedHTTPSValidation();
        RestAssured.proxy("127.0.0.1",8888);
        baseURI="http://localhost:8080";
        RestAssured.filters((req,res,context)->{ //全局的所有接口请求头都会加上cookie
            req.cookie("test1","12345");
            return context.next(req,res);
        });
        RestAssured.filters((req,res,ctx)->{
           Response response=ctx.next(req,res);//全局获取接口响应
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(response);
            responseBuilder.setBody(Base64.getDecoder().decode(response.getBody().asString()));
            responseBuilder.setContentType(ContentType.JSON);//text转为json
            return responseBuilder.build();
        });


    }

    public String token(){
        String DataToken=given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post("/pinter/bank/api/login2?userName=admin&password=1234")
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
                .get("/pinter/bank/api/query2?userName=admin")
                .then()
                .statusCode(200)
                .extract().response().asString(); //获取响应文本
        String json= JsonPath.read(data,"$.message");//使用jayway第三方 json解析
        System.out.println("//使用jayway第三方 json解析"+json);
        Assert.assertEquals(json,"success");


    }

}
