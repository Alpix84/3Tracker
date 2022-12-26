package com.example.a3tracker.DataClasses

import com.google.gson.annotations.SerializedName

data class GetCURequest(
    @SerializedName("token")
    val token : String
)
