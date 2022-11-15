package com.example.newsapp

import android.app.Application
import com.example.newsapp.data.Repository
import com.example.newsapp.network.Api
import com.example.newsapp.network.NewsManager

/**
 * Created by Patrick Adutwum on 14/11/2022.
 */
class MainApp: Application() {
    private val newsManager by lazy {
       NewsManager(Api.retrofitService)
    }
    val repository by lazy {
     Repository(newsManager = newsManager)
    }
}