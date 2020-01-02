import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.ResponseAwareMatcherComposer.*;

/**
 * Created by xiaofenShentu on 2019/12/24 21:32
 */
public class Xueqiu {

    //get请求的demo
    @Test
    public void  testSearch(){

        Response response=
        given()
                .queryParam("symbol","SOGO").header("Cookie","device_id=24700f9f1986800ab4fcc880530dd0ed; s=cc115imsnh; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; u=721577194240213; Hm_lvt_1db88642e346389874251b5a1eded6e3=1576228837,1576487113,1577194242; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577194311")
        .when()
                .get("https://stock.xueqiu.com/v5/stock/batch/quote.json")
         .then()
                .log().all().statusCode(200)
                //添加不同的断言
                .body("data.items.quote.symbol",hasItem("SOGO"))
                .body("data.items.market.status_id",hasItem(7))
                .body("data.items[0].market.time_zone",equalTo("America/New_York"))
                .body("data.items[0].market.time_zone",startsWith("America"))
                .body("data.items[0].market.time_zone",endsWith("New_York"))
               // find用法
                .body("data.items.market.find{it.status_id==7}.time_zone",equalTo("America/New_York"))
                .body("data.items.quote.find{it.code == 'SOGO'}.name",equalTo("搜狗"))
                .extract().response();

        System.out.println("输出获取到的内容");
        String S=response.path("data.items[0].market.time_zone").toString();
        System.out.println(S);


    }
}
