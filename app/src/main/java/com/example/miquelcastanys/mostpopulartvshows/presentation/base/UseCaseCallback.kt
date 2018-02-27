package com.example.miquelcastanys.mostpopulartvshows.presentation.base

interface UseCase <in T>  {
    fun onSuccess(item: T)
    fun onError(code: Int)
}