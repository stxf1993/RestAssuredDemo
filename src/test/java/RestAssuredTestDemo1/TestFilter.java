package RestAssuredTestDemo1;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;


/**
 * Created by xiaofenShentu on 2019/12/27 11:35
 */
public class TestFilter {


    //给每个请求都设置Cookie
    @BeforeClass
    public static void setup(){
        //设置全局的filter
 /*       RestAssured.filters((req,res,ctx)->{
            //设置header中的cookie
            req.header("Cookie","device_id=24700f9f1986800ab4fcc880530dd0ed; s=cc115imsnh; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; u=721577194240213; Hm_lvt_1db88642e346389874251b5a1eded6e3=1576228837,1576487113,1577194242; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577194311");
           //next()发送请求
            Response resOrigin=ctx.next(req,res);
            //返回原始响应结果
            return resOrigin;
        });*/
    }




   /* @BeforeClass
    //篡改响应结果
    public static void setup(){
        RestAssured.filters((req, res, ctx)->{
            //先发送一次请求拿到响应结果
            Response resOrigin=ctx.next(req,res);
            //初始化一个ResponseBuilder用来修改Response，并复制原始内容到Builer
            ResponseBuilder responseBuilder=new ResponseBuilder().clone(resOrigin);
            //修改响应内容
            responseBuilder.setBody("new body");
            // 生成新的响应结果
            Response resnew=responseBuilder.build();
            //返回新的响应结果
            return resnew;

        });
    }
    */




    //get请求的demo
    @Test
    public void  testSearch(){

        Response response=
                given()
                        .queryParam("symbol","SOGO")
                        //.header("Cookie","device_id=24700f9f1986800ab4fcc880530dd0ed; s=cc115imsnh; xq_a_token=c9d3b00a3bd89b210c0024ce7a2e049f437d4df3; xq_r_token=8712d4cae3deaa2f3a3d130127db7a20adc86fb2; u=721577194240213; Hm_lvt_1db88642e346389874251b5a1eded6e3=1576228837,1576487113,1577194242; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1577194311")
                        .log().all()
                 .when()
                        .get("https://stock.xueqiu.com/v5/stock/batch/quote.json")
                .then()
                        .log().all().statusCode(200)
                        //添加不同的断言
                       // .body("data.items.quote.symbol",hasItem("SOGO"))
                        .extract().response();



    }
}