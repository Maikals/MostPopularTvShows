package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BasePresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseView
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail


interface TvShowDetailContract {
    interface View : BaseView<Presenter> {
        fun getTvShowDetailOk(tvShowDetail: TvShowDetail)
        fun getTvShowDetailKo(errorCode: String)
        fun getSimilarTvShowsListOk(tvShowList: List<BaseListItem>)
        fun getSimilarTvShowListKo(errorCode: String)
    }

    interface Presenter : BasePresenter<Context, View> {
        fun getTvShowDetail()
        fun getSimilarTvShowList()
        fun openSimilarTvShowCompleteList()
        fun openTvShowDetail(position: Int, view: android.view.View)
    }
}