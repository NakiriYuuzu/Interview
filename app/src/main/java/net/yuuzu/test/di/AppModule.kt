package net.yuuzu.test.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import net.yuuzu.test.data.DataApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDataApi(): DataApi {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/winwiniosapp/interview/main/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(DataApi::class.java)
    }
}