package day4;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;

public class fileuploads {

    //uploading
    //@Test
    void singleFile(){
        File f=new File("C:\\Users\\Happy\\OneDrive\\Desktop\\Selenium_All\\API_Testing\\Pavan Sir\\Day9_StoreAPI_Testcases.xlsx");
        given().contentType("multipart/form-data")
                .multiPart("file",f)
                .when().post("https://the-internet.herokuapp.com/upload")
                .then().statusCode(200)
                .log().all();
    }

    //@Test
    void multipleFile(){
        File f1=new File("C:\\Users\\Happy\\OneDrive\\Desktop\\Selenium_All\\API_Testing\\Pavan Sir\\Day9_StoreAPI_Testcases.xlsx");
        File f2=new File("C:\\Users\\Happy\\OneDrive\\Desktop\\Selenium_All\\API_Testing\\Pavan Sir\\Day9_StoreAPI_Testcases.xlsx");

        given().contentType("multipart/form-data")
                .multiPart("file",f1)
                .multiPart("file",f2)
                .when().post("https://the-internet.herokuapp.com/upload")
                .then().statusCode(200)
                .log().all();
    }

    @Test
    void downloadFile(){
        given()
                .when().get("https://the-internet.herokuapp.com/download/test.txt")
                .then().statusCode(200).log().all();
    }


}
