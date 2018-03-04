package com.example.miquelcastanys.mostpopulartvshows.presentation.useCases

import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSource
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse


class GetSimilarTvShowsListUseCase(private val repository: MostPopularTvShowsSourceImpl) {
    fun getAsync(id: Int, apiKey: String, language: String, page: Int, callback: UseCaseCallback<TvShowListResponse>) {
        repository.getSimilarTvShowsList(id, apiKey, language, page, object : MostPopularTvShowsSource.GetSimilarTvShowsListCallback {
            override fun onSuccess(tvShowListResponse: TvShowListResponse) {
                callback.onSuccess(tvShowListResponse)
            }

            override fun onFailure(errorCode: Int) {
                callback.onError(errorCode)
            }

        })
    }
}