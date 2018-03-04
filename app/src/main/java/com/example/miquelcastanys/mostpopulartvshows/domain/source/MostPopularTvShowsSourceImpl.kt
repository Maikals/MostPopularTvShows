package com.example.miquelcastanys.mostpopulartvshows.domain.source

import com.example.miquelcastanys.mostpopulartvshows.BuildConfig
import com.example.miquelcastanys.mostpopulartvshows.domain.api.MostPopularTvShowsService
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTvShowsSourceImpl : MostPopularTvShowsSource {
    override fun getMostPopularTvShowsList(apiKey: String,
                                           language: String,
                                           page: Int,
                                           callback: MostPopularTvShowsSource.GetMostPopularTvShowsListCallback) {

        val mostPopularTvShowsList = MostPopularTvShowsService.getService().getMostPopularTvShowsList(apiKey, language, page)
        if (BuildConfig.DEBUG) println("URL -> " + mostPopularTvShowsList.request().url())
        mostPopularTvShowsList.enqueue(object : Callback<TvShowListResponse> {

            override fun onResponse(call: Call<TvShowListResponse>?,
                                    response: Response<TvShowListResponse>?) {
                if (response?.code() == 200) {
                    callback.onSuccess(response.body()!!)
                } else
                    callback.onFailure(response?.code() ?: 500)
            }

            override fun onFailure(call: Call<TvShowListResponse>?, t: Throwable?) {
                callback.onFailure(500)
            }

        })
    }

    override fun getTvShowDetail(id: Int, apiKey: String, language: String, callback: MostPopularTvShowsSource.GetTvShowDetailCallback) {
        val tvShowDetail = MostPopularTvShowsService.getService().getTvShowDetail(id, apiKey, language)
        if (BuildConfig.DEBUG) println("URL -> " + tvShowDetail.request().url())
        tvShowDetail.enqueue(object : Callback<TvShowDetailResponse> {
            override fun onResponse(call: Call<TvShowDetailResponse>?, response: Response<TvShowDetailResponse>?) {
                if (response?.code() == 200) {
                    callback.onSuccess(response.body()!!)
                } else
                    callback.onFailure(response?.code() ?: 500)            }

            override fun onFailure(call: Call<TvShowDetailResponse>?, t: Throwable?) {
                callback.onFailure(500)
            }
        })

    }

    override fun getSimilarTvShowsList(id: Int, apiKey: String, language: String, page: Int, callback: MostPopularTvShowsSource.GetSimilarTvShowsListCallback) {
        val mostPopularTvShowsList = MostPopularTvShowsService.getService().getSimilarTvShowsList(id, apiKey, language, page)
        if (BuildConfig.DEBUG) println("URL -> " + mostPopularTvShowsList.request().url())
        mostPopularTvShowsList.enqueue(object : Callback<TvShowListResponse> {

            override fun onResponse(call: Call<TvShowListResponse>?,
                                    response: Response<TvShowListResponse>?) {
                if (response?.code() == 200) {
                    callback.onSuccess(response.body()!!)
                } else
                    callback.onFailure(response?.code() ?: 500)
            }

            override fun onFailure(call: Call<TvShowListResponse>?, t: Throwable?) {
                callback.onFailure(500)
            }

        })
    }
}