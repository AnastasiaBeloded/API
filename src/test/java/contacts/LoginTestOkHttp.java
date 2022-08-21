package contacts;

import com.google.gson.Gson;
import dto.AuthRequestDto;
import dto.AuthResponseDto;
import dto.ErrorDto;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestOkHttp {
    Gson gson = new Gson();
    public  static final MediaType JSON = MediaType.get("application/json;charset=utf-8");
    OkHttpClient client = new OkHttpClient();

    @Test
    public void loginSuccess() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("noa@gmail.com").password("Nnoa12345$").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
        AuthResponseDto responseDto = gson.fromJson(response.body().string(),AuthResponseDto.class);
        responseDto.getToken();
        System.out.println(responseDto.getToken());
    }
    @Test
    public void loginUnSuccess() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("miagmail.com").password("Nnoa12345$").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);
        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(errorDto.getCode(),400);
        Assert.assertEquals(errorDto.getMessage(),"Wrong email format! Example: name@mail.com");

    }
    @Test
    public void loginUnSuccess1() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("noa@gmail.com").password("Nnoa12345").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);
        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(errorDto.getCode(),400);
        Assert.assertEquals(errorDto.getMessage(),"Password must contain at least one special symbol from ['$','~','-','_']!");
    }
    @Test
    public void loginUnSuccess2() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("noa@gmail.com").password("Winter").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        Assert.assertEquals(response.code(),400);
        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(errorDto.getCode(),400);
        Assert.assertEquals(errorDto.getMessage(),"Password length need be 8 or more symbols");
    }
    @Test
    public void loginUnSuccess3() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("winwin@gmail.com").password("Win12345$").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/login")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertFalse(response.isSuccessful());
        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(errorDto.getMessage(),"Wrong email or password!");
       // Assert.assertEquals(response.code(),401);
        Assert.assertEquals(errorDto.getCode(),401);
    }
    @Test
    public void RegistrationSuccess() throws IOException {
        AuthRequestDto auth = AuthRequestDto.builder().email("nonam@gmail.com").password("Winter12345$").build();

        RequestBody requestBody = RequestBody.create(gson.toJson(auth),JSON);

        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/registration")
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        Assert.assertEquals(response.code(),200);
    }
}
