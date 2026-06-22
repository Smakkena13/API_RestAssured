package day6;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.*;

public class Assignment1 {

    @Test
    public void ass1() throws FileNotFoundException {
        File f=new File(".\\src\\test\\java\\day6\\two.json");
        FileReader fr=new FileReader(f);
        JSONTokener jt=new JSONTokener(fr);
        JSONObject jo=new JSONObject(jt);

        JsonPath jp=new JsonPath(jo.toString());
        String id=jp.getString("id");
        System.out.println("id: "+id);

    }
}
