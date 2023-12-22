package com.projectnewsappsalt.presentation.articles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projectnewsappsalt.core.utils.enums.Category
import com.projectnewsappsalt.core.utils.enums.State
import com.projectnewsappsalt.presentation.articles.components.ArticleCardItem
import com.projectnewsappsalt.presentation.articles.components.CategoryListItem
import com.projectnewsappsalt.presentation.articles.components.shimmerLoadingAnimation
import com.projectnewsappsalt.presentation.articles.viewmodel.ArticlesEvent
import com.projectnewsappsalt.presentation.articles.viewmodel.ArticlesUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesScreen(
    state: ArticlesUiState,
    event: (ArticlesEvent) -> Unit,
    snackBarHostState: SnackbarHostState,
    navigateToDetail: (urlLink: String, articleTitle: String) -> Unit,
) {
    val categoryList = Category.entries
    var selectedCategory by rememberSaveable {
        mutableStateOf(categoryList[0])
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Top News")
            })
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            LazyRow(
                contentPadding = PaddingValues(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(items = categoryList) { category ->
                    CategoryListItem(
                        categoryName = category.value,
                        onClick = {
                            selectedCategory = category
                            event(ArticlesEvent.OnGetArticleList(category = selectedCategory))
                        },
                        isChipSelected = (category == selectedCategory),
                    )
                }
            }

            when (state.states) {
                State.LOADING -> ArticlesLoadingContent()
                State.SUCCESS -> ArticlesSuccessContent(
                    state = state,
                    navigateToDetail = navigateToDetail,
                )

                State.ERROR -> ArticleErrorContent(state = state)
                else -> {}
            }
        }
    }
}

@Composable
fun ArticlesSuccessContent(
    state: ArticlesUiState,
    navigateToDetail: (urlLink: String, articleTitle: String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(items = state.articleList) { article ->
            ArticleCardItem(
                data = article,
                onClick = {
                    navigateToDetail(article.url, article.title)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
            )
        }
    }
}

@Composable
fun ArticlesLoadingContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        repeat(4) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(20.dp))
                    .clip(shape = RoundedCornerShape(20.dp))
                    .shimmerLoadingAnimation()
            )
        }
    }
}

@Composable
fun ArticleErrorContent(
    state: ArticlesUiState,
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = state.articleError.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
            )
        )
    }
}