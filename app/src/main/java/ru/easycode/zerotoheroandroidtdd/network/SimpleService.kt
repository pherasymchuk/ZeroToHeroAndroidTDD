package ru.easycode.zerotoheroandroidtdd.network

import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleService {
    @GET("{fullUrl}")
    suspend fun fetch(@Path(value = "fullUrl", encoded = true) url: String): SimpleResponse
}


