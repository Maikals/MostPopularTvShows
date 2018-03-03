package com.example.miquelcastanys.mostpopulartvshows.domain.api

import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {
    @GET("tv/popular")
    fun getMostPopularTvShowsList(@Query("api_key") apiKey: String,
                                  @Query("language") language: String,
                                  @Query("page") page: Int): Call<MostPopularTvShowListResponse>

    @GET("tv/{tvShowId}")
    fun getTvShowDetail(@Path("tvShowId") tvShowId: Int,
                        @Query("api_key") apiKey: String,
                        @Query("language") language: String): Call<TvShowDetailResponse>
}