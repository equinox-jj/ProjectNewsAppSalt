package com.projectnewsappsalt.core.base

sealed interface Resources<out T> {
    data object Loading : Resources<Nothing>
    data class Success<T>(val data: T? = null) : Resources<T>
    data class Error(val message: String? = null) : Resources<Nothing>
}