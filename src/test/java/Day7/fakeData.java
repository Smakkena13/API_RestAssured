package Day7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class fakeData {

    @Test
    void fakeer(){
        Faker faker=new Faker();

        String fullname=faker.name().fullName();
        String firstname=faker.name().firstName();
        String cellPhone=faker.phoneNumber().cellPhone();
        String pokemon=faker.pokemon().name();

        System.out.println(fullname+" "+firstname+" "+cellPhone+" "+pokemon);
    }
}
