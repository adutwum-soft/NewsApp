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
    suspend fun getTopArticles(@Query("country") country: String): TopNewsResponse

    @GET("top-headlines")
    suspend fun getArticlesByCategory(@Query("category") category: String, @Query("language") lan: String): TopNewsResponse

    @GET("everything")
    suspend fun getArticleBySources(@Query("sources") sources: String): TopNewsResponse

    @GET("everything")
    suspend fun getArticleByQuery(@Query("q") query: String): TopNewsResponse
}