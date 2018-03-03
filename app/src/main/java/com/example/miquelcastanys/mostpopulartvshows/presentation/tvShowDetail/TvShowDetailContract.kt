package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BasePresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseView
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail


interface TvShowDetailContract {
    interface View : BaseView<Presenter> {
        fun getTvShowDetailOk(tvShowDetail: TvShowDetail)
        fun getTvShowDetailKo(errorCode: String)
    }

    interface Presenter : BasePresenter<Context, View> {
        fun getTvShowDetail()
    }
}