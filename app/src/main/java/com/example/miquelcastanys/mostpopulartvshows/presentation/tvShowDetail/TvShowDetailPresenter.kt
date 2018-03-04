package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.TvShowDetailMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers.TvShowsListMapper
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.similarTvShowsList.SimilarTvShowsListActivity
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.GetSimilarTvShowsListUseCase
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.TvShowDetailUseCase
import com.example.miquelcastanys.mostpopulartvshows.presentation.util.PresentationConstants
import java.lang.ref.WeakReference


class TvShowDetailPresenter(val id: Int) : TvShowDetailContract.Presenter {

    private var context: WeakReference<Context>? = null
    private var view: WeakReference<TvShowDetailContract.View>? = null
    private var repository: MostPopularTvShowsSourceImpl? = null
    private val similarTvShowsList: ArrayList<BaseListItem> = ArrayList()
    private var tvShowDetail: TvShowDetail? = null


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
                    PresentationConstants.API_KEY,
                    PresentationConstants.LANGUAGE,
                    object : UseCaseCallback<TvShowDetailResponse> {
                        override fun onSuccess(item: TvShowDetailResponse) {
                            tvShowDetail = TvShowDetailMapper.turnInto(item)
                            view?.get()?.getTvShowDetailOk(tvShowDetail!!)
                            view?.get()?.showProgressBar(false)
                        }

                        override fun onError(code: Int) {
                            view?.get()?.getTvShowDetailKo(code.toString())
                            view?.get()?.showProgressBar(false)
                        }
                    })
        }
    }

    override fun getSimilarTvShowList() {
        repository.let {
            GetSimilarTvShowsListUseCase(it!!).getAsync(id,
                    PresentationConstants.API_KEY,
                    PresentationConstants.LANGUAGE,
                    1,
                    object : UseCaseCallback<TvShowListResponse> {
                        override fun onSuccess(item: TvShowListResponse) {
                            similarTvShowsList.addAll(TvShowsListMapper.turnInto(item))
                            view?.get()?.getSimilarTvShowsListOk(similarTvShowsList)
                        }

                        override fun onError(code: Int) {
                            view?.get()?.getTvShowDetailKo(code.toString())
                        }

                    })
        }
    }

    override fun openSimilarTvShowCompleteList() {
        val intent = Intent(context?.get(), SimilarTvShowsListActivity::class.java)
        intent.putExtra(SimilarTvShowsListActivity.TV_SHOW_TITLE_EXTRA, tvShowDetail?.name)
        intent.putExtra(SimilarTvShowsListActivity.TV_SHOW_ID_EXTRA, id)
        context?.get()?.startActivity(intent)
        (context?.get() as AppCompatActivity).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun openTvShowDetail(position: Int, view: View) {
        val transitionName = context?.get()?.getString(R.string.transition_string)
        val options =
                ActivityOptionsCompat.makeSceneTransitionAnimation((context?.get()!! as? AppCompatActivity),
                        view, // Starting view
                        transitionName    // The String
                )
        val intent = TvShowDetailActivity.getIntent(context?.get()!!,
                (similarTvShowsList[position] as? TvShowListItem)!!)
        ActivityCompat.startActivity(context?.get()!!, intent, options.toBundle())
    }
}