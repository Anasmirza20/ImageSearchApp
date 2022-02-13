package com.example.epayapplication.di

import com.example.epayapplication.api.ApiInterface
import com.example.epayapplication.api.ApiInterface.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getRetrofitInstance(): Retrofit {
        val okHttpClient = OkHttpClient()
        val interceptor = HttpLoggingInterceptor()
        val timeout: Long = 120
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okHttpClient.newBuilder()
                    .connectTimeout(timeout, TimeUnit.SECONDS)
                    .readTimeout(timeout, TimeUnit.SECONDS)
                    .writeTimeout(timeout, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build()
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

}