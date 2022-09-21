package com.example.loginapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface apiset {
    @FormUrlEncoded
    @POST("getuser")
    Call<responsemodel> verifyuser(
            @Field("Name") String Name,
            @Field("Password") String Password
    );
}
