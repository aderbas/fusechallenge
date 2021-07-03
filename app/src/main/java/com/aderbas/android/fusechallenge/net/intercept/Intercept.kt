package com.aderbas.android.fusechallenge.net.intercept

import okhttp3.Interceptor
import okhttp3.Response

class Intercept : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "")
                .build()
        )
    }
}

//var request = chain?.request()
//
//request = request?.newBuilder()
//?.addHeader("Content-Type", "application/json")
//?.addHeader("Accept", "application/json")
//?.addHeader("Authorization", "")
//?.build()
//
//return chain!!.proceed(request)