package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl

interface BasePresenter<in T, in V> {
    fun start()
    fun attach(context: T, view: V, repository: MostPopularTvShowsSourceImpl)
    fun detach()
}