package Day9;

import io.restassured.RestAssured;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class dataDriven {

    static String Bearer = "Bearer 74914dc66b99c8fc6c357cf539b31c501986cf8c02461bcae5337576a379bc4b";
    static String BaseUrl = "https://simple-books-api.click";

    //@Test(dataProvider = "excelDataProvider", dataProviderClass = DataProviders.class)
    void testExcel(String bookid,String cname){
        testSubmitorder(bookid,cname);
    }

//    @Test(dataProvider = "jsonDataProvider", dataProviderClass = DataProviders.class)
    void testJson(Map<String,String> data){
        testSubmitorder(data.get("BookID"),data.get("CustomerName"));
    }

    @Test(dataProvider = "csvDataProvider", dataProviderClass = DataProviders.class)
    void testCsv(String bookid,String cname){
        testSubmitorder(bookid,cname);
    }

    void testSubmitorder(String bookid,String cname) {

        RestAssured.baseURI = BaseUrl;

        // Create order
        JSONObject jo = new JSONObject();
        jo.put("bookId", Integer.parseInt(bookid));
        jo.put("customerName", cname);

        String orid = given()
                .header("Authorization", Bearer)
                .contentType("application/json")
                .body(jo.toString())
                .when()
                .post("/orders")
                .then()
                .log().all()
                .statusCode(201)
                .extract().response()
                .jsonPath().getString("orderId");

        System.out.println("Order ID: " + orid);

        // Delete order
        given()
                .pathParam("id", orid)
                .header("Authorization", Bearer)
                .when()
                .delete("/orders/{id}")
                .then()
                .statusCode(204)
                .log().all();

        System.out.println("Order deleted: " + orid);
    }
}
