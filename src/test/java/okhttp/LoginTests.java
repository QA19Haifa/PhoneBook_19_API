package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTests {

public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

@Test
        public void loginPositive() throws IOException {

    AuthRequestDTO requestDTO = AuthRequestDTO.builder()
            .username("abc@def.com")
            .password("$Abcdef12345")
            .build();

    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    RequestBody body = RequestBody.create(gson.toJson(requestDTO), JSON);

    Request request = new Request.Builder()
            .url("https://contactapp-telran-backend.herokuapp.com/v1/user/login/usernamepassword")
            .post(body)
            .build();

    Response response = client.newCall(request).execute();

    if(response.isSuccessful()){
        String responseJson = response.body().string();
        AuthResponseDTO responseDTO = gson.fromJson(responseJson, AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());
        System.out.println("Response code is ---> " + response.code());
        Assert.assertTrue(response.isSuccessful());
    } else {
        System.out.println("Response code is ---> " + response.code());
        ErrorDTO errorDTO = gson.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getMessage() + " " + errorDTO.getError());
        Assert.assertTrue(response.isSuccessful());
    }
}

// token = eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoiYWJjQGRlZi5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDAzOSwiaWF0IjoxNjkwMjIwMDM5fQ.KKjPur1pqoqRQCy03hGqixxTD_Vaft6QSlOMHcSAiuM
}
