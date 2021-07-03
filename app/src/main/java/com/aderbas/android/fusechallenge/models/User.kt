package com.aderbas.android.fusechallenge.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("location")
    val location : String,
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("profile_image_url_https")
    val picUrl : String
)