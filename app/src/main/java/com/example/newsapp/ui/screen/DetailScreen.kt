package com.example.newsapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.models.NewsData

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun DetailsScreen(navController: NavController, newsData: NewsData){
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Details Screen", fontWeight = FontWeight.SemiBold)
        Button(onClick = {
//            navController.navigate("TopNews")
            navController.popBackStack()
        }) {
            Text(text = "Go to TopNews Screen ${newsData.author}")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview(){
//    DetailsScreen(rememberNavController())
}