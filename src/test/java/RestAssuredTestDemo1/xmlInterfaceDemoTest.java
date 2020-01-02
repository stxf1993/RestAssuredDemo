package RestAssuredTestDemo1;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.response.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

/**
 * Created by xiaofenShentu on 2019/12/26 13:42
 * 用于练习传递xml格式请求
 * 练习xml断言
 */
public class xmlInterfaceDemoTest {



    @Test
    public void getStudetnList(){
        given()
                .log().all()
        .when()
                .get("http://localhost:8082/getXmlValue/getXml2")
        .then()
                .log().all().statusCode(200)
                 .body("**.find{it.name.equals(\"sunny\")}.className",equalTo("一班"));


               /* //第一个学生是不是一班的
                .body("studentList.student[0].className",equalTo("一班"))
                //查看student元素个数
                .body("studentList.student.size()",equalTo(2))
                .body("studentList.student.size()",equalTo(2))
                //@表示属性 ，查看年龄18的学生，爱跳舞的有哪些
                 .body("studentList.student.findAll{it.@number==18}.hobbys.hobby",hasItem("跳舞"))
                // 查看一班的学生里有没有喜欢跳舞的
                .body("studentList.student.findAll{it.className.equals(\"一班\")}.hobbys.hobby",hasItem("跳舞"))
                //xml定位中可以用**来代替前面的寻址
                .body("**.findAll{it.@number==18}.hobbys.hobby",hasItem("跳舞"));*/

    }


    /**
     * 传递一个xml参数到post请求
     */

  @Test
    public void getStudetnList2(){
        String s ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "\n" +
                "<student name=\"stxf\" number=\"16\" sex=\"girl\">\n" +
                "        <className>二班</className>\n" +
                "        <hobbys>\n" +
                "            <hobby>唱歌</hobby>\n" +
                "            <hobby>跳舞</hobby>\n" +
                "        </hobbys>\n" +
                "</student>\n";
        given()
                .log().all().contentType("application/xml").body(s)
        .when()
                .post("http://localhost:8082/getXmlValue/getXml3")
        .then()
                .log().all().statusCode(200);
    }



}
