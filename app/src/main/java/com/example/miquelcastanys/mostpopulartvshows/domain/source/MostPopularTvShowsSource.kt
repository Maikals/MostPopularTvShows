package com.example.miquelcastanys.mostpopulartvshows.domain.source

import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse


interface MostPopularTvShowsSource {
    interface GetMostPopularTvShowsListCallback {
        fun onSuccess(tvShowListResponse: TvShowListResponse)
        fun onFailure(errorCode: Int)
    }
    interface GetTvShowDetailCallback{
        fun onSuccess(tvShowDetailResponse: TvShowDetailResponse)
        fun onFailure(errorCode: Int)
    }

    fun getMostPopularTvShowsList(apiKey: String,
                                  language: String,
                                  page: Int,
                                  callback: GetMostPopularTvShowsListCallback)
    fun getTvShowDetail(id: Int,
                        apiKey: String,
                        language: String,
                        callback: GetTvShowDetailCallback)
}