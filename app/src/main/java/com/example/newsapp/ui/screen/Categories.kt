package com.example.newsapp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.models.getAllArticleCategories
import com.example.newsapp.models.getArticleCategory
import com.example.newsapp.network.NewsManager

/**
 * Created by Patrick Adutwum on 22/10/2022.
 */

@Composable
fun Categories(onFetchCategory: (String) -> Unit = {}, newsManager: NewsManager){
    val tabsItems = getAllArticleCategories()
    Column() {
        LazyRow{
            items(tabsItems.size){
                val category = tabsItems[it]
                CategoriesTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }
    }

}

@Composable
fun CategoriesTab(category: String, isSelected: Boolean = false, onFetchCategory: (String) -> Unit){
    val background = if (isSelected) colorResource(id = R.color.purple_200) else colorResource(id = R.color.purple_700)
    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 16.dp)
            .clickable { onFetchCategory(category) },
        shape = CircleShape,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
