package com.example.newsapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.MockData
import com.example.newsapp.ui.screen.DetailsScreen
import com.example.newsapp.ui.screen.TopNews

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun NewsApp(){
    Navigation()
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val scrollState = rememberScrollState()
    NavHost(navController = navController, startDestination = "TopNews" , modifier = Modifier.background(
        Color.White)){
        composable("TopNews"){
            TopNews(navController = navController)
        }
        composable("Detail/{newsId}",
            arguments = listOf(
                navArgument("newsId"){
                    type = NavType.IntType
                }
            )
        ){
            val id = it.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
            DetailsScreen(navController = navController, newsData, scrollState)
        }
    }
}