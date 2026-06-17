package day5;

import io.restassured.path.json.JsonPath;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class jsonParse1 {
    public JSONObject parsejson() throws FileNotFoundException {

        File file=new File("C:\\Users\\Happy\\IdeaProjects\\API_RestAssured\\src\\test\\java\\day5\\complex.json");
        FileReader fr=null;
        try{
            fr=new FileReader(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        JSONTokener jt=new JSONTokener(fr);
        JSONObject jo=new JSONObject(jt);
        return jo;
    }

    @Test
    void userValidation() throws FileNotFoundException {
        JsonPath jp=new JsonPath(parsejson().toString());

        //valiadte status
        String s1=jp.getString("status");
        assertThat(s1,is("success"));

        // Validate the id, name, and email
        int id1=jp.getInt("data.userDetails.id");
        String name=jp.getString("data.userDetails.name");
        String email=jp.getString("data.userDetails.email");

        assertThat(id1,is(12345));
        assertThat(name,is("John Doe"));
        assertThat(email,is("john.doe@example.com"));

        // first phone number is of type home with the value 123-456-7890
        String typ=jp.getString("data.userDetails.phoneNumbers[0].type");
        String num=jp.getString("data.userDetails.phoneNumbers[0].number");

        assertThat(typ,is("home"));
        assertThat(num,is("123-456-7890"));

        //geo coordinates
        double lat=jp.getDouble("data.userDetails.address.geo.latitude");
        double lon=jp.getDouble("data.userDetails.address.geo.longitude");
        assertThat(lat,is(39.7817));
        assertThat(lon,is(-89.6501));

        // has enabled notifications and is using the "dark" theme
        boolean noti=jp.getBoolean("data.userDetails.preferences.notifications");
        String th=jp.getString("data.userDetails.preferences.theme");

        assertThat(noti,is(true));
        assertThat(th,is("dark"));
    }
}
