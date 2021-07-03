package com.aderbas.android.fusechallenge.net.intercept

import okhttp3.Interceptor
import okhttp3.Response

class Intercept : Interceptor {

    private val token: String = "AAAAAAAAAAAAAAAAAAAAAPHURAEAAAAAvftHXoqrC5Z7GIZO5vwVyAWnGHA%3D6M1JCK18SwpSkGZg6rWcDRj6rQuGIGWEfb7Mi2QW1r9pZfLWAP"

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }
}