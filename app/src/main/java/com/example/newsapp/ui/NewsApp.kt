package com.example.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.MockData
import com.example.newsapp.components.BottomMenu
import com.example.newsapp.models.ArticleCategory
import com.example.newsapp.models.BottomMenuScreen
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.screen.Categories
import com.example.newsapp.ui.screen.DetailsScreen
import com.example.newsapp.ui.screen.Sources
import com.example.newsapp.ui.screen.TopNews

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun NewsApp(){
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController, scrollState)
}

@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState){
    Scaffold(
        bottomBar = { BottomMenu(navController) },
    ) {
        Navigation(navController = navController, scrollState = scrollState, paddingValues = it)
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager(),
    paddingValues: PaddingValues
){
    val articles = mutableListOf(TopNewsArticle())
    articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))

    Log.d("news", "$articles")

    NavHost(
        navController = navController,
        startDestination = BottomMenuScreen.TopNews.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ){
        bottomNavigation(navController, articles = articles, newsManager = newsManager)
//        composable(BottomMenuScreen.TopNews.route){
//            TopNews(navController = navController, articles = articles!!)
//        }
        composable("Detail/{index}",
            arguments = listOf(
                navArgument("index"){
                    type = NavType.IntType
                }
            )
        ){
            val index = it.arguments?.getInt("index")
            index?.let{
                if (newsManager.query.value.isNotEmpty()){
                    articles.clear()
                    articles.addAll(newsManager.searchNews.value.articles ?: listOf())
                }else{
                    articles.clear()
                    articles.addAll(newsManager.newsResponse.value.articles ?: listOf())
                }
                val article = articles[index]
                DetailsScreen(navController = navController, article, scrollState)
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController, articles: List<TopNewsArticle>, newsManager: NewsManager){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController, articles = articles, query = newsManager.query, newsManager = newsManager)
    }
    composable(BottomMenuScreen.Categories.route){
        newsManager.getArticlesByCategory(ArticleCategory.BUSINESS.categoryName)
        newsManager.onSelectedCategoryChanged(ArticleCategory.BUSINESS.categoryName)

        Categories(newsManager = newsManager, onFetchCategory = {
            newsManager.onSelectedCategoryChanged(it)
            newsManager.getArticlesByCategory(it)
        })
    }
    composable(BottomMenuScreen.Sources.route){
        Sources(newsManager = newsManager)
    }
}