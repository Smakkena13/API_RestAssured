package Day8;

import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class serializationDesialization {
    String stuid;
    @Test
    public void testSerialization(){
        String cour[]={"selenium","c#"};
        Student stu=new Student("4","msk","hyd","12344321",cour);

        stuid=given().body(stu)
                .when().post("http://localhost:3000/students")
                .then().statusCode(201)
                .extract().response().jsonPath().getString("id");
    }

    @Test(dependsOnMethods = {"testSerialization"})
    void testDesialization(){
        Response resp=given().pathParam("sid",stuid)
                .when().get("http://localhost:3000/students/{sid}")
                .then().statusCode(200)
                .extract().response();

        String idd=resp.jsonPath().getString("id");
        Student student=resp.as(Student.class);
        assertThat(student.getName(),equalTo("msk"));
        assertThat(student.getCourses()[0],equalTo("selenium"));
        assertThat(student.getLocation(),equalTo("hyd"));
    }

    @AfterClass
    void deleteStu(){
        given().when().delete("http://localhost:3000/students/"+stuid)
                .then().statusCode(200).log().all();
        System.out.println("deleted");
    }
}
