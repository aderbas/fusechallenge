package com.aderbas.android.fusechallenge.net.retrofit.service

import com.aderbas.android.fusechallenge.models.SearchResult
import com.aderbas.android.fusechallenge.models.Twitter
import com.aderbas.android.fusechallenge.net.retrofit.conf.RetrofitConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwitterService {
    @GET("search/tweets.json")
    fun search(@Query("q") query : String) : Call<SearchResult>

    @GET("statuses/user_timeline.json")
    fun getTwitterTimeline(@Query("screen_name") name : String) : Call<List<Twitter>>

    companion object {
        fun create() : TwitterService {
            val retrofit = RetrofitConfig.build()
            return retrofit.create(TwitterService::class.java)
        }
    }
}