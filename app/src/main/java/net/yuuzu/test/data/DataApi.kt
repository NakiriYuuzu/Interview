package net.yuuzu.test.data

import retrofit2.http.GET

interface DataApi {
    @GET("interview.json")
    suspend fun getData(): ApiResponse
}