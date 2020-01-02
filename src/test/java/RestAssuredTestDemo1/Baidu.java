package RestAssuredTestDemo1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Created by xiaofenShentu on 2019/12/24 21:04
 */
public class Baidu {
    @Test
    public void testGetHtml(){
       /* given().get("http://www.baidu.com").then().statusCode(200);*/

        //打印中间发生的请求
        given()
                .log().all().get("http://www.baidu.com")
                .then().log().all().statusCode(200);
    }
    @Test
    public void testMp3(){

        given()
                .queryParam("wd","mp3")
        .when()
                .get("http://www.baidu.com/s")
        .then()
                .log().all()
                .statusCode(200);

    }


}
