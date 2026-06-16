package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class types_of_payloads {
        String studentid;

//    @Test
    void createStu_Hashmap(){
        HashMap<String,Object> data=new HashMap<>();
        data.put("name","msk");
        data.put("location","chennai");
        data.put("phone","12345");

        String cources[]={"c","c++"};
        data.put("courses",cources);

        studentid=given().contentType("application/json")
                .body(data)
                .when().post("http://localhost:3000/students")
                .then().statusCode(201)
                .body("courses[0]",equalTo("c"))
                .body("courses[1]",equalTo("c++"))
                .log().all()
                .extract().jsonPath().getString("id");
    }

    @AfterMethod
    void deleteStudent(){
    given().when().delete("http://localhost:3000/students/"+studentid)
            .then().statusCode(200).log().all();
        System.out.println("deleted");
    }

//    @Test
    public void createStu_JsonObj(){
        JSONObject jo=new JSONObject();
        jo.put("name","msk");
        jo.put("location","chennai");
        jo.put("phone","12345");

        String cources[]={"c","c++"};
        jo.put("courses",cources);

        studentid=given().contentType("application/json")
                .body(jo.toString())
                .when().post("http://localhost:3000/students")
                .then().statusCode(201)
                .body("courses[0]",equalTo("c"))
                .body("courses[1]",equalTo("c++"))
                .log().all()
                .extract().jsonPath().getString("id");
    }


//    @Test
    void createStu_Pojo(){
        StudentPojo sp=new StudentPojo();
        sp.setId("123");
        sp.setLocation("chennai");
        sp.setName("msk");
        sp.setPhone("12345");
        String c[]={"c","c++"};
        sp.setCourses(c);

        studentid=given().contentType("application/json")
                .body(sp)
                .when().post("http://localhost:3000/students")
                .then().statusCode(201)
                .body("courses[0]",equalTo(sp.getCourses()[0]))
                .body("courses[1]",equalTo(sp.getCourses()[1]))
                .log().all()
                .extract().jsonPath().getString("id");
    }

    @Test
    void createStu_ExternalFile() throws FileNotFoundException {
        File f=new File(".\\src\\test\\java\\day2\\one.json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);

        JSONObject jo=new JSONObject(jt);
        studentid=given().contentType("application/json")
                .body(jo.toString())
                .when().post("http://localhost:3000/students")
                .then().statusCode(201)
                .body("courses[0]",equalTo("java"))
                .body("courses[1]",equalTo("selenium"))
                .log().all()
                .extract().jsonPath().getString("id");

    }
}
