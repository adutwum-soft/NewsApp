package com.example.newsapp.models

import com.example.newsapp.models.ArticleCategory.*

/**
 * Created by Patrick Adutwum on 25/10/2022.
 */
enum class ArticleCategory(val categoryName: String) {
     BUSINESS("business"),
     ENTERTAINMENT("entertainment"),
     GENERAL("general"),
     HEALTH("health"),
     SCIENCE("science"),
     SPORTS("sports"),
     TECHNOLOGY("technology")
}

fun getAllArticleCategories(): List<ArticleCategory>{
    return listOf(
     BUSINESS,
     ENTERTAINMENT,
     GENERAL,
     HEALTH,
     SCIENCE,
     SPORTS,
     TECHNOLOGY,
    )
}

fun getArticleCategory(category: String) : ArticleCategory?{
    val map = values().associateBy(ArticleCategory::categoryName)
    return map[category]
}