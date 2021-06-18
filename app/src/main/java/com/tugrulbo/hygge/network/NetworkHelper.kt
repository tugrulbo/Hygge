package com.tugrulbo.hygge.network

import com.tugrulbo.hygge.BuildConfig
import com.tugrulbo.hygge.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkHelper {

    var productsData: GetProductsData?=null

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://alpkaraosmanoglu.com.tr/appcent/main_page.php/")
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productsData = retrofit.create(GetProductsData::class.java)
    }

    private fun getClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        httpClient.readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        httpClient.writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
        httpClient.addInterceptor(createHttpLoggingInterceptor(BuildConfig.DEBUG))
        return httpClient.build()
    }

    private fun createHttpLoggingInterceptor(debugMode: Boolean): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (debugMode) httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }
}