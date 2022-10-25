package com.example.newsapp.network

import com.example.newsapp.interfaces.NewsService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

     private val retrofit = Retrofit.Builder()
         .addConverterFactory(MoshiConverterFactory.create(moshi))
          .baseUrl(BASE_URL)
          .build()

    val retrofitService: NewsService by lazy{
          retrofit.create(NewsService::class.java)
    }
}