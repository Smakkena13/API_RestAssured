package day6;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Assignment2 {
    @Test
    public void ass2() throws FileNotFoundException {
        File f=new File(".\\src\\test\\java\\day6\\three.json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject jo=new JSONObject(jt);

        JsonPath jp=new JsonPath(jo.toString());

        //Get the purchase amount of the course.
        int pamt=jp.getInt("dashboard.purchaseAmount");
        assertThat(pamt,is(1060));

        //Get the title of the first course (Selenium).
        String title1=jp.getString("courses[0].title");
        assertThat(title1,is("Selenium"));

        // Get the total copies sold for Postman.
        int couSize=jp.getInt("courses.size()");
        for(int i=0;i<couSize;i++){
            String title=jp.getString("courses["+i+"].title");
            if(title.equals("Postman")){
                int copies=jp.getInt("courses["+i+"].copies");
                System.out.println("copies: "+copies);
            }
        }

        // Count the total number of courses
        System.out.println("total courses: "+couSize);

        //Print all the course titles.
        for(int i=0;i<couSize;i++){
            String title=jp.getString("courses["+i+"].title");
            System.out.println("title: "+i+" "+title);
        }
    }

}
