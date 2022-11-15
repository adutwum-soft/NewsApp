package com.example.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.components.BottomMenu
import com.example.newsapp.models.ArticleCategory
import com.example.newsapp.models.BottomMenuScreen
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.network.Api
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.screen.Categories
import com.example.newsapp.ui.screen.DetailsScreen
import com.example.newsapp.ui.screen.Sources
import com.example.newsapp.ui.screen.TopNews

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun NewsApp(mainViewModel: MainViewModel){
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController, scrollState, viewModel = mainViewModel)
}

@Composable
fun MainScreen(navController: NavHostController,
               scrollState: ScrollState,
               viewModel: MainViewModel
){
    Scaffold(
        bottomBar = { BottomMenu(navController) },
    ) {
        Navigation(navController = navController, scrollState = scrollState, paddingValues = it, viewModel = viewModel)
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    newsManager: NewsManager = NewsManager(Api.retrofitService),
    paddingValues: PaddingValues,
    viewModel: MainViewModel
){
    val articles = mutableListOf(TopNewsArticle())
    val topArticles = viewModel.newsResponse.collectAsState().value.articles
    articles.addAll(topArticles ?: listOf())

    Log.d("news", "$articles")

    NavHost(
        navController = navController,
        startDestination = BottomMenuScreen.TopNews.route,
        modifier = Modifier.padding(paddingValues = paddingValues)
    ){
        val queryState = mutableStateOf(viewModel.query.value)
        bottomNavigation(navController, articles = articles, query = queryState, viewModel = viewModel)
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
                if (queryState.value != ""){
                    articles.clear()
                    articles.addAll(viewModel.searchNews.value.articles ?: listOf())
                }else{
                    articles.clear()
                    articles.addAll(viewModel.newsResponse.value.articles ?: listOf())
                }
                val article = articles[index]
                DetailsScreen(navController = navController, article, scrollState)
            }
        }
    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController,
                                     articles: List<TopNewsArticle>,
                                     query: MutableState<String>,
                                     viewModel: MainViewModel
){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController, articles = articles, query = query, viewModel = viewModel)
    }
    composable(BottomMenuScreen.Categories.route){
        viewModel.getArticlesByCategory(ArticleCategory.BUSINESS.categoryName)
        viewModel.onSelectedCategoryChange(ArticleCategory.BUSINESS.categoryName)

        Categories(viewModel = viewModel, onFetchCategory = {
            viewModel.onSelectedCategoryChange(it)
            viewModel.getArticlesByCategory(it)
        })
    }
    composable(BottomMenuScreen.Sources.route){
        Sources(viewModel = viewModel)
    }
}