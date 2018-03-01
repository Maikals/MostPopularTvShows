package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BasePresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseView


interface MostPopularTvShowsListContract {
    interface View : BaseView<Presenter> {
        fun getMostPopularTvShowsListOk(tvShowsList: List<BaseListItem>)
        fun getMostPopularTvShowsListKo(errorMessage: String)
    }

    interface Presenter : BasePresenter<Context, View> {
        fun getMostPopularTvShowsList()
        fun openTvShowDetail(position: Int)
        var isLastPage: Boolean
    }
}