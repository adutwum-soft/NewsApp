package com.example.newsapp.interfaces

import com.example.newsapp.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Patrick Adutwum on 25/10/2022.
 */
interface NewsService {
    @GET("top-headlines")
    fun getTopArticles(@Query("country") country: String): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategory(@Query("category") category: String): Call<TopNewsResponse>

    @GET("everything")
    fun getArticleBySources(@Query("sources") sources: String): Call<TopNewsResponse>
}