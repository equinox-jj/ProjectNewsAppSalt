package com.projectnewsappsalt.data.datasource.remote

import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.data.datasource.remote.model.ArticlesResponse

interface NewsRemoteDataSource {
    suspend fun getArticleList(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
    ): Resources<ArticlesResponse>
}