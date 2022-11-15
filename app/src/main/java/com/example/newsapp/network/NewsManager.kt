package com.example.newsapp.network

import com.example.newsapp.interfaces.NewsService
import com.example.newsapp.models.TopNewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Patrick Adutwum on 25/10/2022.
 */
class NewsManager(private val service: NewsService) {

    suspend fun getSearchedArticles(query: String): TopNewsResponse = withContext(Dispatchers.IO){
        service.getArticleByQuery(query = query)
    }

    suspend fun getArticles(country: String): TopNewsResponse = withContext(Dispatchers.IO){
        service.getTopArticles(country = country)
    }

    suspend fun getArticlesByCategory(category: String): TopNewsResponse = withContext(Dispatchers.IO){
        service.getArticlesByCategory(category = category, "en")
    }

    suspend fun getArticlesBySource(source: String): TopNewsResponse = withContext(Dispatchers.IO){
        service.getArticleBySources(sources = source)
    }
}