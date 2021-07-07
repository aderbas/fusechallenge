package com.aderbas.android.fusechallenge.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id")
    val id : String,
    @SerializedName("name")
    val name : String,
    @SerializedName("location")
    val location : String,
    @SerializedName("created_at")
    val createdAt : String,
    @SerializedName("screen_name")
    val screen_name : String,
    @SerializedName("profile_image_url_https")
    val picUrl : String,
    @SerializedName("profile_background_image_url_https")
    val profileBackground : String
) : Serializable