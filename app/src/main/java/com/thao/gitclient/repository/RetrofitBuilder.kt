package com.thao.gitclient.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    val BASE_URL = "https://api.github.com/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val clientService: IAPI by lazy {
        retrofit.create(IAPI::class.java)
    }
    val apiClient = APIClient(clientService);

}