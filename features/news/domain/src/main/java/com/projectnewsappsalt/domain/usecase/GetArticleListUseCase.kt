package com.projectnewsappsalt.domain.usecase

import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.domain.entities.ArticleEntity
import com.projectnewsappsalt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticleListUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
    ): Flow<Resources<List<ArticleEntity>>> {
        return newsRepository.getArticleList(
            country = country,
            category = category,
            sources = sources,
        )
    }
}