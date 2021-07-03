package com.aderbas.android.fusechallenge.net.retrofit.conf

import com.aderbas.android.fusechallenge.net.intercept.Intercept
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {
    private val client = OkHttpClient.Builder()
        .addInterceptor(Intercept())
        .build();
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.twitter.com/1.1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }
}