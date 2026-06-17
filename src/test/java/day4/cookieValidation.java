package day4;

import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class cookieValidation {
    @Test
    void cookie(){
        Response res=given()
                .when().get("https://www.google.com/")
                .then()
                .cookie("AEC",notNullValue())
                .log().cookies().extract().response();

        String s1=res.getCookie("NID");
        System.out.println(s1);

        Map<String,String> s2=res.getCookies();

        Cookie c= res.getDetailedCookie("AEC");
        System.out.println(c.getDomain());

    }

    @Test
    void valiHead(){
        given()
                .when().get("https://www.google.com/")
                .then().header("content-type",containsString("text/html;"))
                .header("server", "gws")
                .log().headers();
    }

}
