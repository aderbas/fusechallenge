package com.aderbas.android.fusechallenge.models

import com.google.gson.annotations.SerializedName

data class Twitter(
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("id")
    val id : String,
    @SerializedName("text")
    val bodyText : String,
    @SerializedName("user")
    val user: User
)
