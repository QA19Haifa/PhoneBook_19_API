package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class UpdateContactTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDAzOSwiaWF0IjoxNjkwMjIwMDM5fQ.KKjPur1pqoqRQCy03hGqixxTD_Vaft6QSlOMHcSAiuM";
    String id;
    ContactDTO contactDTO;

    @BeforeMethod
    public void precondition(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";


        int i = new Random().nextInt(1000) + 1000;

        contactDTO = ContactDTO.builder()
                .name("QA19")
                .lastName("Automation")
                .email("qa19" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Haifa")
                .description("Students")
                .build();

        String message = given()
                .header("Authorization", token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post("contacts")
                .then()
                .extract()
                .path("message");

        id = message.substring(message.lastIndexOf(" ") + 1);
    }

    @Test
    public void updateContactPositive(){

        String name = contactDTO.getName();
        System.out.printf("name = " + name);
        contactDTO.setId(id);
        contactDTO.setName("QA19_Updated");

        given()
                .body(contactDTO)
                .header("Authorization", token)
                .contentType(ContentType.JSON)
                .when()
                .put("contacts")
                .then()
                .assertThat().statusCode(200)
                .assertThat().body("message", containsString("Contact was updated"));

}

}
