package com.example.newsapp.models

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */
data class TopNewsResponse(
   val status: String? = null,
   val totalResults: Int? = null,
   val articles: List<TopNewsArticle>? = null
)
