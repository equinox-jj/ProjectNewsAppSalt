package com.projectnewsappsalt.data.mapper

import com.projectnewsappsalt.data.datasource.remote.model.ArticlesResponse
import com.projectnewsappsalt.data.datasource.remote.model.Source
import com.projectnewsappsalt.domain.entities.ArticleEntity
import com.projectnewsappsalt.domain.entities.SourceEntity

fun ArticlesResponse.toDomain(): List<ArticleEntity> {
    return this.articles.mapNotNull {
        ArticleEntity(
            author = it?.author.orEmpty(),
            content = it?.content.orEmpty(),
            description = it?.description.orEmpty(),
            publishedAt = it?.publishedAt.orEmpty(),
            source = it?.source?.toDomain(),
            title = it?.title.orEmpty(),
            url = it?.url.orEmpty(),
            urlToImage = it?.urlToImage.orEmpty(),
        )
    }
}

fun Source.toDomain(): SourceEntity {
    return SourceEntity(
        name = this.name.orEmpty(),
        id = this.id.orEmpty(),
    )
}