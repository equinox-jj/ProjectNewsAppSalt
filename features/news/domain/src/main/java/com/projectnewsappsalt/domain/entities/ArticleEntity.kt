package com.projectnewsappsalt.domain.entities

data class ArticleEntity(
    val author: String = "",
    val content: String = "",
    val description: String = "",
    val publishedAt: String = "",
    val source: SourceEntity? = SourceEntity(),
    val title: String = "",
    val url: String = "",
    val urlToImage: String = "",
)