package com.example.miquelcastanys.mostpopulartvshows.domain.api

object MostPopularTvShowsService {
    fun getService(): ServerApi = MostPopularTvShowsRestApiClient.createService(ServerApi::class.java)
}