package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class RegistrationTests {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void registrationPositive(){
        int i = new Random().nextInt(1000) + 1000;

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("abc" + i + "@def.com")
                .password("$Abcdef12345")
                .build();

       String token = given()
               .body(auth)
               .contentType(ContentType.JSON)
               .when()
               .post("user/registration/usernamepassword")
               .then()
               .assertThat().statusCode(200)
               .extract()
               .path("token");

        System.out.println(token);
    }

    @Test
    public void registrationNegativeWrongEmail(){
        int i = new Random().nextInt(1000) + 1000;

        AuthRequestDTO auth = AuthRequestDTO.builder()
                .username("abc" + i + "def.com")
                .password("$Abcdef12345")
                .build();

       given()
               .body(auth)
               .contentType(ContentType.JSON)
               .when()
               .post("user/registration/usernamepassword")
               .then()
               .assertThat().statusCode(400)
               .assertThat().body("message.username", containsString("must be a well-formed email address"));

    }


}
