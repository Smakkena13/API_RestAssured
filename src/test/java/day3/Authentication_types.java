package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Authentication_types {

    //@Test
    void basic(){
        given().auth().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .time(lessThan(5000L))
                .log().all();
    }

    //@Test
    void preeBasic(){
        given().auth().preemptive().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .time(lessThan(5000L))
                .log().all();
    }

    //@Test
    void DigestAuth(){
        given().auth().digest("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .time(lessThan(5000L))
                .log().all();
    }

    //@Test
    void beararAuth(){
        given().header("Authorization","Bearer avabafsfdgd")
                .when().post("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }

    //@Test
    void apiKeyAuth(){
        given().queryParam("key","qaclick123")
                .when().post("https://postman-echo.com/basic-auth")
                .then()
                .statusCode(200)
                .log().all();
    }

}
