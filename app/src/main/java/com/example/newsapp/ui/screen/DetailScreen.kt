package com.example.newsapp.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.MockData
import com.example.newsapp.MockData.getTimeAgo
import com.example.newsapp.R
import com.example.newsapp.models.TopNewsArticle
import com.skydoves.landscapist.coil.CoilImage

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun DetailsScreen(navController: NavController, article: TopNewsArticle, scrollState: ScrollState){
    Scaffold(
        topBar = {
        DetailAppBar(onBackPressed = { navController.popBackStack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Details Screen", fontWeight = FontWeight.SemiBold, color = Color.Black)
            CoilImage(
                imageModel = article.urlToImage,
                contentScale = ContentScale.Crop,
                error = ImageBitmap.imageResource(R.drawable.breaking_news),
                placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news)
            )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = article.author?: "Not Available")
                InfoWithIcon(Icons.Default.DateRange, info = MockData.stringToDate(article.publishedAt!!).getTimeAgo())
            }
            Text(text = article.title?: "Title not available", fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text(text = article.description?: "Description not Available", fontWeight = FontWeight.SemiBold, color = Color.Black, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

@Composable
fun DetailAppBar(onBackPressed: ()-> Unit = {} ){
    TopAppBar(
        title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    )
}

@Composable
fun InfoWithIcon(icon: ImageVector, info: String){
    Row() {
        Icon(
            imageVector = icon,
            contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(id = R.color.purple_500)
        )
        Text(text = info, color = Color.Black)
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview(){
//    DetailsScreen(rememberNavController())
}