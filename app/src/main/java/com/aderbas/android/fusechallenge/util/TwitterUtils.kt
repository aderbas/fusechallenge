package com.aderbas.android.fusechallenge.util

import com.aderbas.android.fusechallenge.net.retrofit.conf.RetrofitConfig
import com.aderbas.android.fusechallenge.net.retrofit.service.TwitterService

class TwitterUtils {
    private val endpoint = RetrofitConfig.buildService(TwitterService::class.java)

}