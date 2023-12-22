package com.projectnewsappsalt.data.repositoy

import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.data.datasource.remote.NewsRemoteDataSource
import com.projectnewsappsalt.data.mapper.toDomain
import com.projectnewsappsalt.domain.entities.ArticleEntity
import com.projectnewsappsalt.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException

class NewsRepositoryImpl(private val newsRemoteDataSource: NewsRemoteDataSource) : NewsRepository {
    override fun getArticleList(
        country: String?,
        category: String?,
        sources: String?,
    ): Flow<Resources<List<ArticleEntity>>> = flow {
        emit(Resources.Loading)
        try {
            val result = newsRemoteDataSource.getArticleList(
                country = country,
                category = category,
                sources = sources,
            )
            when (result) {
                is Resources.Error -> emit(Resources.Error(result.message))
                is Resources.Success -> emit(Resources.Success(result.data?.toDomain()))
                else -> {}
            }
        } catch (e: SocketTimeoutException) {
            emit(Resources.Error(e.message))
        } catch (e: HttpException) {
            emit(Resources.Error(e.message))
        } catch (e: Exception) {
            emit(Resources.Error(e.message))
        }
    }
}