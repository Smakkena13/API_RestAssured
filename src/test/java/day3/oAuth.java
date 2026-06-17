package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class oAuth {
    @Test
    void oauth(){
        String token=given()
                .formParam("client_id","asdvfrdfrv")
                .formParam("client_secreat","password")
                .formParam("grant_type","client_crenditials")
                .when().post("abc.com")
                .then()
                .extract().jsonPath().getString("acces_token");

        given()
                .auth().oauth2(token)
                .when().get("xyx.com")
                .then()
                .statusCode(200)
                .log().all();

    }
}
