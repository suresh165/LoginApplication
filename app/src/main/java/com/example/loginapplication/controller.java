package com.example.loginapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class controller {
    private static final String url="http://192.168.43.94:130/api/values/";
    private static controller clienobject;
    private static Retrofit retrofit;

    controller()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized controller getInstance()
    {
        if(clienobject==null)
            clienobject=new controller();
        return  clienobject;
    }

    apiset getapi()
    {
        return retrofit.create(apiset.class);
    }
}
