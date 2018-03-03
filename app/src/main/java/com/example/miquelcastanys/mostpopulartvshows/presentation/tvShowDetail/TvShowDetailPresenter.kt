package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.content.Context
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.TvShowDetailMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.TvShowsListMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.FooterListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.GetSimilarTvShowsListUseCase
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.TvShowDetailUseCase
import java.lang.ref.WeakReference


class TvShowDetailPresenter(val id: Int) : TvShowDetailContract.Presenter {
    private var context: WeakReference<Context>? = null
    private var view: WeakReference<TvShowDetailContract.View>? = null
    private var repository: MostPopularTvShowsSourceImpl? = null
    private val similarTvShowsList: ArrayList<BaseListItem> = ArrayList()

    override fun start() {
        view?.get()?.showProgressBar(true)
        getTvShowDetail()
        getSimilarTvShowList()
    }

    override fun attach(context: Context, view: TvShowDetailContract.View, repository: MostPopularTvShowsSourceImpl) {
        this.context = WeakReference(context)
        this.view = WeakReference(view)
        this.repository = repository
    }

    override fun detach() {
        context?.clear()
        view?.clear()
        repository = null
    }

    override fun getTvShowDetail() {
        repository.let {
            TvShowDetailUseCase(it!!).getAsync(id,
                    "98d3f21f52adf59ccbf65cb76683d73b",
                    "en-US",
                    object : UseCaseCallback<TvShowDetailResponse> {
                        override fun onSuccess(item: TvShowDetailResponse) {
                            view?.get()?.getTvShowDetailOk(TvShowDetailMapper.turnInto(item))
                            view?.get()?.showProgressBar(false)
                        }

                        override fun onError(code: Int) {
                            view?.get()?.getTvShowDetailKo(code.toString())
                            view?.get()?.showProgressBar(false)
                        }
                    })
        }
    }

    private fun addFooter() {
        similarTvShowsList.add(FooterListItem())
    }

    override fun getSimilarTvShowList() {
        repository.let {
            GetSimilarTvShowsListUseCase(it!!).getAync(id,
                    "98d3f21f52adf59ccbf65cb76683d73b",
                    "en-US",
                    1,
                    object : UseCaseCallback<TvShowListResponse> {
                        override fun onSuccess(item: TvShowListResponse) {
                            similarTvShowsList.addAll(TvShowsListMapper.turnInto(item))
                            addFooter()
                            view?.get()?.getSimilarTvShowsListOk(similarTvShowsList)
                        }

                        override fun onError(code: Int) {
                            view?.get()?.getTvShowDetailKo(code.toString())
                        }

                    })
        }
    }
}