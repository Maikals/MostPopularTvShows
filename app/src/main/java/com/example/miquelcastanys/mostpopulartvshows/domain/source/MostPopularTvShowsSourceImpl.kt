package com.example.miquelcastanys.mostpopulartvshows.domain.source

import com.example.miquelcastanys.mostpopulartvshows.domain.api.MostPopularTvShowsService
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MostPopularTvShowsSourceImpl : MostPopularTvShowsSource {
    override fun getMostPopularTvShowsList(apiKey: String,
                                           language: String,
                                           page: Int,
                                           callback: MostPopularTvShowsSource.GetMostPopularTvShowsListCallback) {
        val mostPopularTvShowsList = MostPopularTvShowsService.getService().getMostPopularTvShowsList(apiKey, language, page)
        mostPopularTvShowsList.enqueue(object : Callback<MostPopularTvShowListResponse> {

            override fun onResponse(call: Call<MostPopularTvShowListResponse>?,
                                    response: Response<MostPopularTvShowListResponse>?) {
                if (response?.code() == 200)
                    callback.onSuccess(response.body()!!)
                else
                    callback.onFailure(response?.code() ?: 500)
            }

            override fun onFailure(call: Call<MostPopularTvShowListResponse>?, t: Throwable?) {
                callback.onFailure(500)
            }

        })
    }
}