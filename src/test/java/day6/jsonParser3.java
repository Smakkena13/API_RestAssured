package day6;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class jsonParser3 {
    @Test
    void jsonParse3() throws FileNotFoundException {

        File f1 = new File("C:\\Users\\Happy\\IdeaProjects\\API_RestAssured\\src\\test\\java\\day6\\one.json");
        FileReader fr = new FileReader(f1);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject jo = new JSONObject(jt);

        JsonPath jp=new JsonPath(jo.toString());
        int pa=jp.getInt("dashboard.purchaseAmount");
        assertThat(pa,is(1060));

        String title=jp.getString("courses[0].title");
        assertThat(title,is("Selenium"));

        int courses=jp.getInt("courses.size()");
        System.out.println("courses count: "+courses);
        for(int i=0;i<courses;i++){
            String ti=jp.getString("courses["+i+"].title");
            if(ti.equals("Postman")){
                int copies=jp.getInt("courses["+i+"].copies");
                System.out.println("copies: "+copies);
            }
        }

        for(int i=0;i<courses;i++) {
            String ti = jp.getString("courses[" + i + "].title");
            System.out.println(ti);
        }


    }
}

