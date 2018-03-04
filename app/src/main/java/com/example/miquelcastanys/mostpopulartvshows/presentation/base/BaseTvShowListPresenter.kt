package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.content.Context
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.TvShowsListMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.FooterListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowsListContract
import com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail.TvShowDetailActivity
import java.lang.ref.WeakReference


abstract class BaseTvShowListPresenter(override var isLastPage: Boolean = false) : TvShowsListContract.Presenter {
    private var context: WeakReference<Context>? = null
    private var view: WeakReference<TvShowsListContract.View>? = null
    protected var repository: MostPopularTvShowsSourceImpl? = null
    private var tvShowsList: ArrayList<BaseListItem> = ArrayList()
    protected var currentPage = 1
    private var clearList: Boolean = false
    protected val useCaseCallback = object : UseCaseCallback<TvShowListResponse> {
        override fun onSuccess(item: TvShowListResponse) {
            manageResult(item)
            view?.get()?.showProgressBar(false)
        }

        override fun onError(code: Int) {
            --currentPage
            view?.get()?.getTvShowsListKo("There is an error")
            view?.get()?.showProgressBar(false)
        }

    }

    override fun start() {
        view?.get()?.showProgressBar(true)
        currentPage = 1
        clearList = true
        getTvShowsList()
    }

    override fun attach(context: Context, view: TvShowsListContract.View, repository: MostPopularTvShowsSourceImpl) {
        this.context = WeakReference(context)
        this.view = WeakReference(view)
        this.repository = repository
    }

    override fun detach() {
        context?.clear()
        view?.clear()
        repository = null
    }

    private fun manageResult(item: TvShowListResponse) {
        if (clearList) {
            tvShowsList.clear()
            clearList = false
        }
        removeFooter()
        setIsLastPage(item.page, item.total_pages)
        addResultToTvShowList(TvShowsListMapper.turnInto(item))
        if (!isLastPage) addFooter()
        view?.get()?.getTvShowsListOk(tvShowsList)
    }

    private fun addFooter() {
        tvShowsList.add(FooterListItem())
    }

    private fun addResultToTvShowList(tvShowListResult: ArrayList<BaseListItem>) {
        tvShowsList.addAll(tvShowListResult)
    }

    private fun setIsLastPage(page: Int, totalPages: Int) {
        isLastPage = page == totalPages
    }

    private fun removeFooter() {
        tvShowsList.removeAll { it is FooterListItem }
    }


    override fun openTvShowDetail(position: Int, view: android.view.View) {
        val transitionName = context?.get()?.getString(R.string.transition_string)
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((context?.get()!! as? AppCompatActivity),
                        view, // Starting view
                        transitionName    // The String
                )
        val tvShowListItem = tvShowsList[position] as? TvShowListItem
        val intent = TvShowDetailActivity.getIntent(context?.get()!!, tvShowListItem!!)
        ActivityCompat.startActivity(context?.get()!!, intent, options.toBundle())
    }
}