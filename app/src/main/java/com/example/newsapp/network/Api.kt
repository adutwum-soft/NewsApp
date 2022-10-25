package com.example.newsapp.network

import com.example.newsapp.interfaces.NewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Patrick Adutwum on 25/10/2022.
 */
object Api {
     private const val BASE_URL = "https://newsapi.org/v2/"
     const val API_KEY = "7c2809da69a9497c8cc72cd3be5caad4"
     private val moshi = Moshi.Builder()
         .add(KotlinJsonAdapterFactory())
         .build()

    val logging = HttpLoggingInterceptor()

    val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(
            Interceptor{ chain ->
                val builder = chain.request().newBuilder()
                builder.header("X-Api-Key", API_KEY)
                return@Interceptor chain.proceed(builder.build())
            }
        )
        logging.level = HttpLoggingInterceptor.Level.BODY
        addNetworkInterceptor(logging)
    }.build()

     private val retrofit = Retrofit.Builder()
         .addConverterFactory(MoshiConverterFactory.create(moshi))
         .client(httpClient)
          .baseUrl(BASE_URL)
          .build()

    val retrofitService: NewsService by lazy{
          retrofit.create(NewsService::class.java)
    }
}