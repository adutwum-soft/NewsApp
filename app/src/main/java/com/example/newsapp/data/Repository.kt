package com.example.newsapp.data

import com.example.newsapp.network.NewsManager

/**
 * Created by Patrick Adutwum on 14/11/2022.
 */
class Repository(val newsManager: NewsManager) {
    suspend fun getArticles() = newsManager.getArticles(country = "us")
    suspend fun getArticlesByCategory(category: String) = newsManager.getArticlesByCategory(category = category)
    suspend fun getArticleBySources(source: String) = newsManager.getArticlesBySource(source = source)
    suspend fun getSearchedArticles(query: String) = newsManager.getSearchedArticles(query = query)
}