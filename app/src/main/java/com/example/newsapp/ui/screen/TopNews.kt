package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
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
fun TopNews(navController: NavController, articles: List<TopNewsArticle>){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn{
            items(articles.size){index->
                TopNewsItem(
                    article = articles[index],
                    onNewsClicked = { navController.navigate("Detail/$index") }
                )
            }
        }
    }
}

@Composable
fun TopNewsItem(article: TopNewsArticle, onNewsClicked: ()-> Unit = {}){
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClicked()
        },
    ) {
        CoilImage(
            imageModel = article.urlToImage,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.breaking_news),
            placeHolder = ImageBitmap.imageResource(id = R.drawable.breaking_news),
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(top = 16.dp, start = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(text = MockData.stringToDate(article.publishedAt!!).getTimeAgo(), color = Color.White, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(80.dp))
            Text(text = article.title!!, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopNewsPreview(){
//    TopNews(rememberNavController())
}