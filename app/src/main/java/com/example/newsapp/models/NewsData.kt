package com.example.newsapp.models

import com.example.newsapp.R

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */
data class NewsData(
 val id: Int,
 val image: Int = R.drawable.breaking_news,
 val author: String,
 val title: String,
 val description: String,
 val publishedAt:String
) {
}