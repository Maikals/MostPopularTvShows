package com.example.miquelcastanys.mostpopulartvshows.domain.source

import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse


interface MostPopularTvShowsSource {
    interface GetMostPopularTvShowsListCallback {
        fun onSuccess(mostPopularTvShowListResponse: MostPopularTvShowListResponse)
        fun onFailure(errorCode: Int)
    }

    fun getMostPopularTvShowsList(apiKey: String,
                                  language: String,
                                  page: Int,
                                  callback: GetMostPopularTvShowsListCallback)
}