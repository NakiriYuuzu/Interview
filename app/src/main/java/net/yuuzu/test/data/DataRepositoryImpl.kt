package net.yuuzu.test.data

import net.yuuzu.test.domain.DataRepository
import net.yuuzu.test.domain.Resource
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val dataApi: DataApi
) : DataRepository {
    override suspend fun getData(): Resource<ApiResponse> {
        return try {
            val response = dataApi.getData()
            Resource.Success(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}