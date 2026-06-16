package day2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class queryParameter {
    @Test
    void querypm(){
        given().queryParam("page",2)
                .queryParam("id",5)
                .header("x-api-key","free_user_3DTr16erSSR8LwMgwe4vJg8tCnr")
                .when().get("https://reqres.in/api/users")
                .then().statusCode(200)
                .log().all();
    }
}
