package com.example.bkanjula.cameraapp.ServerData;


import com.example.bkanjula.cameraapp.ServerData.data.GetData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/Values/GetLoginData")
    @Headers({"Content-Type:application/x-www-form-urlencoded"})
    Call<List<GetData>> getJSON(@Query("id") String id);



}
