package com.projectnewsappsalt.presentation.di

import com.projectnewsappsalt.presentation.articles.viewmodel.ArticlesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::ArticlesViewModel)
}