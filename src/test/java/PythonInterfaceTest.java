import com.sun.org.apache.regexp.internal.RE;
import io.restassured.response.Response;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;




/**
 * Created by xiaofenShentu on 2019/12/25 13:32
 */
public class PythonInterfaceTest {

    public  static String url="http://localhost:9091";
    public static String access_token;

    //发送json格式的post请求
    @Parameters({"loginname","pwd"})
    @Test
    public void testLogin(String name,String pwd){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("userName",name);
        data.put("pwd",pwd);
        HashMap<String, Map> map=new HashMap<String, Map>();
        map.put("authRequest",data);
        //String string = "{\"authRequest\":{\"userName\":\""+name+"\",\""+pwd+"\":\"pwd\"}}";

        access_token=given()
                .log().all().header("Content-Type","application/json")
                //.contentType("application/json").body(string)
                .contentType("application/json").body(map)
        .when()
                .post("http://localhost:9091/api/v1/user/login")
        .then()
                .log().all().statusCode(200)
       .extract()
               .path("access_token");


        //System.out.println("获取的值是"+access_token);


    }

    @Test //浏览菜单接口
    public void menuListTest(){
        given()
                .log().all()
                .queryParam("type","breakfast")
                .header("access_token",access_token)
                .header("Content-Type","application/json")
       .when()
                .get(url+"/api/v1/menu/list")
       .then()
                .log().all().statusCode(200);


        Response R=given()
                .log().all()
                .queryParam("type","breakfast")
                .header("access_token",access_token)
                .header("Content-Type","application/json")
                .when()
                .get(url+"/api/v1/menu/list")
                .then()
                .log().all().statusCode(200).
                        extract().response();




    }


  @Test //下单接口
    public void menuconfirmTest(){

        String  bodyjson="{\"order_list\":[{\"menu_nunber\":\"01\",\"number\":1}," +
                "{\"menu_nunber\":\"03\",\"number\":2}]}";
        given()
                .log().all().header("access_token",access_token)
                .contentType("application/json")
                .body(bodyjson)
        .when()
                .post(url+"/api/v1/menu/confirm")
        .then()
                .log().all().statusCode(200);
  }


  @Test
  public void logoutTest(){
        given()
                .log().all().header("access_token",access_token)
        .when()
                .delete(url+"/api/v1/user/logout")
        .then()
                .log().all().statusCode(200);

  }

}
