package day2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class pathParameter {
    @Test
    void pathpm(){
        given().pathParam("country","India")
                .when().get("https://restcountries.com/v2/name/{country}")
                .then().statusCode(200)
                .log().all();
    }
}
