package com.example.miquelcastanys.mostpopulartvshows.presentation.useCases

import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSource
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse


class TvShowDetailUseCase(private val repository: MostPopularTvShowsSourceImpl) {
    fun getAsync(id: Int, apiKey: String, language: String, callback: UseCaseCallback<TvShowDetailResponse>) {
        repository.getTvShowDetail(id, apiKey, language, object : MostPopularTvShowsSource.GetTvShowDetailCallback {
            override fun onSuccess(tvShowDetailResponse: TvShowDetailResponse) {
                callback.onSuccess(tvShowDetailResponse)
            }

            override fun onFailure(errorCode: Int) {
                callback.onError(errorCode)
            }

        })
    }
}