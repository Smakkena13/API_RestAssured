package Day7;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class chainingReq {
    int userid;
    String baseURL="https://reqres.in/api/users";
    Faker faker=new Faker();

    @Test(priority=1)
    void createUser(){
        JSONObject jo=new JSONObject();
        jo.put("name",faker.funnyName());
        jo.put("job",faker.artist());

        userid=given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .contentType("application/json")
                .body(jo.toString())
                .when().post(baseURL)
                .then().statusCode(201)
                .body(containsString("id"))
                .log().body()
                .extract().response().jsonPath().getInt("id");

        System.out.println("useris: "+userid);
    }

    @Test
    public void updateUser(){
        JSONObject jo1=new JSONObject();
        jo1.put("name",faker.funnyName());
        jo1.put("job",faker.artist());

        given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .contentType("application/json")
                .pathParam("id",userid)
                .body(jo1.toString())
                .when().put(baseURL+"/{id}")
                .then().statusCode(200)
                .log().body();
    }

    @Test
    public void deleteUser(){
        given().header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .pathParam("id",userid)
                .when().delete(baseURL+"/{id}")
                .then().statusCode(204)
                .body(emptyOrNullString())
                .log().body();
    }
}
