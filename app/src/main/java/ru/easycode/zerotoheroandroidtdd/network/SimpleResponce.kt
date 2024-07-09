package ru.easycode.zerotoheroandroidtdd.network

import com.google.gson.annotations.SerializedName

data class SimpleResponse(
    @SerializedName("text") val text: String,
)
