package com.example.miquelcastanys.mostpopulartvshows.presentation.useCases

import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSource
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse


class MostPopularTvShowsListUseCase(val repository: MostPopularTvShowsSourceImpl) {
    fun getAsync(apiKey: String, language: String, page: Int, callback: UseCaseCallback<TvShowListResponse>) {
        repository.getMostPopularTvShowsList(apiKey,
                language,
                page,
                object : MostPopularTvShowsSource.GetMostPopularTvShowsListCallback {
                    override fun onSuccess(tvShowListResponse: TvShowListResponse) =
                            callback.onSuccess(tvShowListResponse)

                    override fun onFailure(errorCode: Int) =
                        callback.onError(errorCode)
                })
    }
}