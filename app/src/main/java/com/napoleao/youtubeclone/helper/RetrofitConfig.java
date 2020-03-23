package com.napoleao.youtubeclone.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    /**
     * Configurando o Retrofit.
     */
    public static Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(YouTubeConfig.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
