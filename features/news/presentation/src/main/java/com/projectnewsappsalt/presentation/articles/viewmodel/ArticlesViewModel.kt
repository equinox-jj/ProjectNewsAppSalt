package com.projectnewsappsalt.presentation.articles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectnewsappsalt.core.base.Resources
import com.projectnewsappsalt.core.utils.enums.State
import com.projectnewsappsalt.domain.usecase.GetArticleListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticlesViewModel(private val getArticleListUseCase: GetArticleListUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(ArticlesUiState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = Channel<ArticlesEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        onEvent(
            ArticlesEvent.OnGetArticleList()
        )
    }

    fun onEvent(event: ArticlesEvent) {
        when (event) {
            is ArticlesEvent.OnGetArticleList -> getArticleList(
                country = event.country,
                category = event.category.value,
            )

            is ArticlesEvent.ToastArticleErrorMessage -> {
                viewModelScope.launch {
                    _uiEvent.send(ArticlesEvent.ToastArticleErrorMessage(event.message))
                }
            }
        }
    }

    private fun getArticleList(
        country: String? = null,
        category: String? = null,
        sources: String? = null,
        searchQuery: String? = null,
    ) {
        viewModelScope.launch {
            getArticleListUseCase.invoke(
                country = country,
                category = category,
                sources = sources,
            ).collect { resource ->
                when (resource) {
                    Resources.Loading -> {
                        _uiState.update {
                            it.copy(
                                states = State.LOADING,
                                isLoading = true,
                                articleList = emptyList(),
                                articleError = null,
                            )
                        }
                    }

                    is Resources.Success -> {
                        _uiState.update {
                            it.copy(
                                states = State.SUCCESS,
                                isLoading = false,
                                articleList = resource.data.orEmpty(),
                                articleError = null,
                            )
                        }
                    }

                    is Resources.Error -> {
                        _uiState.update {
                            it.copy(
                                states = State.ERROR,
                                isLoading = false,
                                articleList = emptyList(),
                                articleError = resource.message,
                            )
                        }
                        _uiEvent.send(ArticlesEvent.ToastArticleErrorMessage(resource.message.orEmpty()))
                    }
                }
            }
        }
    }
}