package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class basic {

    int userid;

    @Test(priority=1)
    void getUser(){
        given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .header("content-type",equalTo("application/json; charset=utf-8"))
                .body(containsString("email"))
                .body("page",equalTo(2))
//                .time(lessThan(2000L))
                .log().all();
    }

    @Test(priority=2)
    void createUser(){
        HashMap<String,String> data=new HashMap<String,String>();
        data.put("name","msk");
        data.put("job","trainee");

        userid=given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .contentType("application/json")
                .body(data)
                .when().post("https://reqres.in/api/users")
                .then().statusCode(201)
                .body(containsString("id"))
                .log().all()
                .extract().jsonPath().getInt("id");
    }

    @Test(priority=3,dependsOnMethods={"createUser"})
    void updateUser(){
        HashMap<String,String> data=new HashMap<String,String>();
        data.put("name","kummu");
        data.put("job","yoga trainee");

        given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .contentType("application/json")
                .body(data)
                .when().put("https://reqres.in/api/users/"+userid)
                .then().statusCode(200)
                .header("cache-control", equalTo("no-store"))
                .log().all();
    }

    @Test(priority=4,dependsOnMethods = {"createUser","updateUser"})
    void DeleteUser(){
        given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .when().delete("https://reqres.in/api/users/"+userid)
                .then().statusCode(204)
                .body(emptyOrNullString())
                .log().all();
    }
}
