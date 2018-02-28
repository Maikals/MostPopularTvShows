package com.example.miquelcastanys.mostpopulartvshows.presentation.useCases

import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSource
import com.example.miquelcastanys.mostpopulartvshows.domain.source.MostPopularTvShowsSourceImpl
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.UseCaseCallback
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse


class MostPopularTvShowsListUseCase(val repository: MostPopularTvShowsSourceImpl) {
    fun getAsync(apiKey: String, language: String, page: Int, callback: UseCaseCallback<MostPopularTvShowListResponse>) {
        repository.getMostPopularTvShowsList(apiKey,
                language,
                page,
                object : MostPopularTvShowsSource.GetMostPopularTvShowsListCallback {
                    override fun onSuccess(mostPopularTvShowListResponse: MostPopularTvShowListResponse) =
                            callback.onSuccess(mostPopularTvShowListResponse)

                    override fun onFailure(errorCode: Int) =
                        callback.onError(errorCode)
                })
    }
}