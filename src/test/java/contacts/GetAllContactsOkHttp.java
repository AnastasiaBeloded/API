package contacts;

import com.google.gson.Gson;
import dto.ContactDto;
import dto.ErrorDto;
import dto.GetAllContactsDro;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class GetAllContactsOkHttp {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Im5vYUBnbWFpbC5jb20ifQ.G_wfK7FRQLRTPu9bs2iDi2fcs69FHmW-0dTY4v8o5Eo";
@Test
    public void getAllContactsSuccess() throws IOException {
    Request request = new Request.Builder()
            .url("https://contacts-telran.herokuapp.com/api/contact")
            .get()
            .addHeader("Authorization", token)
            .build();
    Response response = client.newCall(request).execute();
    Assert.assertEquals(response.code(),200);

   GetAllContactsDro contacts =gson.fromJson(response.body().string(), GetAllContactsDro.class);
    List<ContactDto>all = contacts.getContacts();
    for (ContactDto dto:all){
        System.out.println(dto.toString());
        System.out.println("*****_********_******");
    }

}
    @Test
    public void getAllWrongToken() throws IOException {
        Request request = new Request.Builder()
                .url("https://contacts-telran.herokuapp.com/api/contact")
                .get()
                .addHeader("Authorization", "gergg")
                .build();
        Response response = client.newCall(request).execute();
        ErrorDto errorDto = gson.fromJson(response.body().string(),ErrorDto.class);
        Assert.assertEquals(errorDto.getMessage(),"Wrong token format!");
        Assert.assertEquals(errorDto.getCode(),401);///bug



    }
}
