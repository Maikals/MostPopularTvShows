package com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain


data class TvShowListResponse(val page: Int,
                              val total_results: Int,
                              val total_pages: Int,
                              val results: List<TvShowResponse>)