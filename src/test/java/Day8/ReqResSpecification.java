package Day8;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

public class ReqResSpecification {
    RequestSpecification requestsp;
    ResponseSpecification responsesp;

    @BeforeClass
    void setup(){
        RequestSpecBuilder requestsb=new RequestSpecBuilder();
        requestsb.setBaseUri("http://localhost:3000/");
        requestsb.setBasePath("employees");
        requestsp=requestsb.build();

        ResponseSpecBuilder responsesb=new ResponseSpecBuilder();
        responsesb.expectStatusCode(200);
        responsesb.expectContentType("application/json");
        responsesp=responsesb.build();
    }

    @Test
    public void getall(){
        given().spec(requestsp)
                .when().get()
                .then().spec(responsesp)
                .log().body();
    }

    @Test
    public void getone(){
        given().queryParam("gender","Female")
                .spec(requestsp)
                .when().get()
                .then().spec(responsesp)
                .body("gender",everyItem(equalTo("Female")))
                .log().body();
    }
}
