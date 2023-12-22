package com.projectnewsappsalt.data.datasource.remote

import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.data.datasource.remote.model.ArticlesResponse
import com.projectnewsappsalt.data.datasource.remote.service.NewsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRemoteDataSourceImpl(private val apiService: NewsApiService) : NewsRemoteDataSource {
    override suspend fun getArticleList(
        country: String?,
        category: String?,
        sources: String?,
    ): Resources<ArticlesResponse> {
        return withContext(Dispatchers.IO) {
            val result = apiService.getArticleList(
                country = country,
                category = category,
                sources = sources,
            )

            if (result.isSuccessful) {
                Resources.Success(result.body())
            } else {
                Resources.Error(result.message())
            }
        }
    }
}