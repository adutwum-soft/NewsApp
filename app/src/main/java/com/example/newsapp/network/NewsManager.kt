package com.example.newsapp.network

import android.util.Log
import androidx.compose.runtime.*
import com.example.newsapp.models.ArticleCategory
import com.example.newsapp.models.TopNewsResponse
import com.example.newsapp.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Patrick Adutwum on 25/10/2022.
 */
class NewsManager {
    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse: State<TopNewsResponse>
        @Composable get() = remember{
            _newsResponse
        }
    val selectedCategory: MutableState<ArticleCategory?> = mutableStateOf(null)
    init {
        getArticles()
    }

    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory: MutableState<TopNewsResponse>
        @Composable get() = remember {
            _getArticleByCategory
        }

    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles("us", Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("news", "${_newsResponse.value }")
                }else{
                    Log.d("error", "Error")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.printStackTrace()}")
            }

        })
    }

    fun getArticlesByCategory(category: String){
        val service = Api.retrofitService.getArticlesByCategory(category, Api.API_KEY)
        service.enqueue(object : Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if (response.isSuccessful){
                    _getArticleByCategory.value = response.body()!!
                    Log.d("category", "${_getArticleByCategory.value }")
                }else{
                    Log.d("error", "${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error", "${t.message}")
            }

        })
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getArticleCategory(category)
        selectedCategory.value = newCategory
    }
}