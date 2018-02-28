package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BasePresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseView


interface MostPopularTvShowsListContract {
    interface View : BaseView<Presenter>
    interface Presenter : BasePresenter<Context, View>
}