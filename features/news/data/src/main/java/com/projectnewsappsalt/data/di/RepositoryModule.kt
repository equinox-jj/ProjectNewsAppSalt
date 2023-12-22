package com.projectnewsappsalt.data.di

import com.projectnewsappsalt.data.repositoy.NewsRepositoryImpl
import com.projectnewsappsalt.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
}