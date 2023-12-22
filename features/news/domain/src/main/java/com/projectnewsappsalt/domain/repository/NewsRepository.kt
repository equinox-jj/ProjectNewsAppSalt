package com.projectnewsappsalt.domain.repository

import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.domain.entities.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getArticleList(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
    ): Flow<Resources<List<ArticleEntity>>>
}