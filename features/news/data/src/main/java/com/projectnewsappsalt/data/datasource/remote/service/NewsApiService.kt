package com.projectnewsappsalt.data.datasource.remote.service

import com.projectnewsappsalt.core.utils.constants.Constants
import com.projectnewsappsalt.data.datasource.remote.model.ArticlesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    suspend fun getArticleList(
        @Query("apiKey") apiKey: String = Constants.API_KEY,
        @Query("pageSize") pageSize: Int = 40,
        @Query("country") country: String? = null, // Can`t mix with sources params
        @Query("category") category: String? = null, // Can`t mix with sources params
        @Query("sources") sources: String? = null, // Can`t mix with country and category params
    ): Response<ArticlesResponse>
}