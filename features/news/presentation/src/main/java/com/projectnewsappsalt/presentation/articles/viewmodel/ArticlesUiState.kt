package com.projectnewsappsalt.presentation.articles.viewmodel

import androidx.compose.runtime.Immutable
import com.projectnewsappsalt.core.utils.enums.Category
import com.projectnewsappsalt.core.utils.enums.State
import com.projectnewsappsalt.domain.entities.ArticleEntity

@Immutable
data class ArticlesUiState(
    val states: State = State.EMPTY,
    val category: Category = Category.BUSINESS,
    val isLoading: Boolean = false,
    val articleList: List<ArticleEntity> = emptyList(),
    val articleError: String? = null,
    val searchQuery: String? = null,
)
