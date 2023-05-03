package com.lambdaTestApp.android;

import okhttp3.*;
import org.apache.http.auth.Credentials;

import java.io.File;
import java.io.IOException;

import static okhttp3.Credentials.basic;
import static org.apache.http.auth.Credentials.*;

public class AppUploadAndroid {
    public static String userName = System.getenv("LT_USERNAME");
     public static String accessKey = System.getenv("LT_ACCESS_KEY");

    public static void upload() throws IOException {

        String credential = basic(userName, accessKey);

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("appFile","apps/WikipediaSample.apk",
                        RequestBody.create(MediaType.parse("application/octet-stream"),
                                new File("apps/WikipediaSample.apk")))
                .addFormDataPart("custom_id","android_appurl")
                .addFormDataPart("name","android_appurl")
                .build();
        Request request = new Request.Builder()
                .url("https://manual-api.lambdatest.com/app/upload/realDevice")
                .method("POST", body)
                .addHeader("Authorization", credential)
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
