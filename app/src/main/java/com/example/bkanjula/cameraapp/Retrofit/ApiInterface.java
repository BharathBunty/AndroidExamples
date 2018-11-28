package com.example.bkanjula.cameraapp.Retrofit;

import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Example;
import com.example.bkanjula.cameraapp.Retrofit.JsonResponse.Post;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/users")
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    Call<Example> getJSON(@Query("page") String page);

    @POST("api/users")
    @FormUrlEncoded
    Call<Post> savePost(@Field("name") String name,
                        @Field("job") String job);


}
