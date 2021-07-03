package com.aderbas.android.fusechallenge.net.retrofit.service

import com.aderbas.android.fusechallenge.models.Twitter
import retrofit2.Call
import retrofit2.http.GET

interface TwitterService {
    @GET("search/tweets.json")
    fun search() : Call<List<Twitter>>
}