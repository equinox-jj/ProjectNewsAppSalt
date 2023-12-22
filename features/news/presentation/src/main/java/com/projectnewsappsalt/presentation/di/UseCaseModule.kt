package com.projectnewsappsalt.presentation.di

import com.projectnewsappsalt.domain.usecase.GetArticleListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetArticleListUseCase(get()) }
}