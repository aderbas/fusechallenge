package com.aderbas.android.fusechallenge.models

import com.google.gson.annotations.SerializedName

data class SearchResult(@SerializedName("statuses") val statuses: List<Twitter>)
