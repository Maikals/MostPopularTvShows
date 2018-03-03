package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.MostPopularTvShowsListMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.FooterListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.MostPopularTvShowsListUseCase
import java.lang.ref.WeakReference


class MostPopularTvShowsListPresenter(override var isLastPage: Boolean = false) : MostPopularTvShowsListContract.Presenter {
    private var context: WeakReference<Context>? = null
    private var view: WeakReference<MostPopularTvShowsListContract.View>? = null
    private var repository: MostPopularTvShowsSourceImpl? = null
    private var tvShowsList: ArrayList<BaseListItem> = ArrayList()
    private var currentPage = 1

    override fun start() {
        view?.get()?.showProgressBar(true)
        tvShowsList.clear()
        currentPage = 1
        getMostPopularTvShowsList()
    }

    override fun attach(context: Context, view: MostPopularTvShowsListContract.View, repository: MostPopularTvShowsSourceImpl) {
        this.context = WeakReference(context)
        this.view = WeakReference(view)
        this.repository = repository
    }

    override fun detach() {
        context?.clear()
        view?.clear()
        repository = null
    }

    override fun getMostPopularTvShowsList() {
        repository.let {
            MostPopularTvShowsListUseCase(it!!)
                    .getAsync("98d3f21f52adf59ccbf65cb76683d73b",
                            "en-US",
                            currentPage++,
                            object : UseCaseCallback<MostPopularTvShowListResponse> {
                                override fun onSuccess(item: MostPopularTvShowListResponse) {
                                    manageResult(item)
                                    view?.get()?.showProgressBar(false)
                                }

                                override fun onError(code: Int) {
                                    --currentPage
                                    view?.get()?.getMostPopularTvShowsListKo("There is an error")
                                    view?.get()?.showProgressBar(false)
                                }

                            })
        }
    }

    private fun manageResult(item: MostPopularTvShowListResponse) {
        removeFooter()
        setIsLastPage(item.page, item.total_pages)
        addResultToMostPopularTvShowList(MostPopularTvShowsListMapper.turnInto(item))
        if (!isLastPage) addFooter()
        view?.get()?.getMostPopularTvShowsListOk(tvShowsList)
    }

    private fun addFooter() {
        tvShowsList.add(FooterListItem())
    }

    private fun addResultToMostPopularTvShowList(tvShowListResult: ArrayList<BaseListItem>) {
        tvShowsList.addAll(tvShowListResult)
    }

    private fun setIsLastPage(page: Int, totalPages: Int) {
        isLastPage = page == totalPages
    }

    private fun removeFooter() {
        tvShowsList.removeAll { it is FooterListItem }
    }

    override fun openTvShowDetail(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}