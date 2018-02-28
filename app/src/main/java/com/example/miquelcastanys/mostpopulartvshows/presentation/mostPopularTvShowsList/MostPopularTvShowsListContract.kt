package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BasePresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseView
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem


interface MostPopularTvShowsListContract {
    interface View : BaseView<Presenter> {
        fun getMostPopularTvShowsListOk(tvShowsList: List<TvShowListItem>)
        fun getMostPopularTvShowsListKo(errorMessage: String)
    }

    interface Presenter : BasePresenter<Context, View> {
        fun getMostPopularTvShowsList()
        fun openTvShowDetail(position: Int)
        var isLastPage: Boolean
    }
}