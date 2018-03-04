package com.example.miquelcastanys.mostpopulartvshows.presentation.base

interface UseCaseCallback<in T>  {
    fun onSuccess(item: T)
    fun onError(code: Int)
}