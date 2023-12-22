package com.projectnewsappsalt.presentation.articles.viewmodel

import com.projectnewsappsalt.core.utils.enums.Category

sealed interface ArticlesEvent {
    data class OnGetArticleList(
        val category: Category = Category.BUSINESS,
        val country: String? = "us",
    ) : ArticlesEvent
    data class ToastArticleErrorMessage(val message: String) : ArticlesEvent
}