package net.yuuzu.test.domain

import net.yuuzu.test.data.ApiResponse

interface DataRepository {
    suspend fun getData(): Resource<ApiResponse>
}