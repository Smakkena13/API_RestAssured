package day6;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class jsonParser2 {
    @Test
    public void storeApi(){
        ResponseBody rp=given().
                when().get("http://localhost:3000/store")
                .then()
                .extract().response().body();

        JsonPath jp=new JsonPath(rp.asString());

        int bookcount=jp.getInt("book.size()");
        System.out.println("bookcount: "+bookcount);

        //print all the data
        for(int i=0;i<bookcount;i++){
            String author=jp.getString("book["+i+"].author");
            String category=jp.getString("book["+i+"].category");
            double price=jp.getDouble("book["+i+"].price");
            String title=jp.getString("book["+i+"].title");

            System.out.println(author+" "+category+" "+price+" "+title);
        }

        //count price
        double totalpr=0;
        for(int i=0;i<bookcount;i++){
            double price=jp.getDouble("book["+i+"].price");

            totalpr=totalpr+price;
        }
        assertThat(totalpr,is(53.92));

        //check "title": "Moby Dick"
        boolean flag=false;
        for(int i=0;i<bookcount;i++){
            String title=jp.getString("book["+i+"].title");
            if(title.equals("Moby Dick")){
                flag=true;
                break;
            }
        }
        assertThat(flag,is(true));


    }
}
