
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class JsonSchemaTest {
    @Test
    void jsonSchemaTest(){
        given()
                .header("hello","Hgowarts")
        .when()
                .get("https://httpbin.ceshiren.com/get")
        .then()
        .log().all()
        .body(matchesJsonSchemaInClasspath("schema.json"));
    }
}
