package com.bithumb.realizer12.di

import com.bithumb.realizer12.BuildConfig
import com.bithumb.realizer12.data.datasource.remote.retrofit.ApiService
import com.bithumb.realizer12.data.datasource.remote.retrofit.ServerIp
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

/**
 * Create Date: 2023/11/20
 *
 *
 * network(retrofit)
 * 관련 의존성 주입 모듈
 *
 * @author LeeDongHun
 *
 *
 **/
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val RETROFIT_WRITE_TIME_OUT = 30L
    private const val RETROFIT_READ_TIME_OUT = 30L
    private const val RETROFIT_CONNECT_TIME_OUT =60L

    //api service 세팅
    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): ApiService =
        retrofit.create(ApiService::class.java)


    //Retrofit 설정 새탕
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(ServerIp.BaseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //okhttp client 의존성 주입
    @Provides
    @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(RETROFIT_CONNECT_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(RETROFIT_READ_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(RETROFIT_WRITE_TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLogInterceptor())
        .build()

    private fun httpLogInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        return loggingInterceptor.setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {//디버그가 아니면  로그가 안보이게 해준다.
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }
}