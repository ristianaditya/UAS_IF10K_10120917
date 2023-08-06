package com.example.uas_if10k_10120917.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://apiv3.apifootball.com/"

    val instance:ApiServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        retrofit.create(ApiServices::class.java)
    }

}