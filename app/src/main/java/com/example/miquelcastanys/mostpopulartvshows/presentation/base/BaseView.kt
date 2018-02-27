package com.example.miquelcastanys.mostpopulartvshows.presentation.base

interface BaseView <in T> {
    fun setPresenter(presenter: T)
}