package RestAssuredTestDemo1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.builder.ResponseBuilder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by xiaofenShentu on 2019/12/24 21:32
 * 练习了json断言
 */
public class Xueqiu {



    //get请求的demo
    @Test
    public void  testSearch(){
        given()
                .queryParam("symbol","SOGO")
                .header("Cookie","device_id=24700f9f1986800ab4fcc880530dd0ed; s=cc115imsnh; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; u=721577194240213; Hm_lvt_1db88642e346389874251b5a1eded6e3=1576228837,1576487113,1577194242; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577194311")
        .when()
                .get("https://stock.xueqiu.com/v5/stock/batch/quote.json")
         .then()
                .log().all().statusCode(200)
                //添加不同的断言
                .body("data.items.quote.symbol",hasItem("SOGO"))
                 .body("data.items.market.status_id",hasItem(2));
    }
}
