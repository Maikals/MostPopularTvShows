package com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers

import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowDetailResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowDetail


object TvShowDetailMapper {
    fun turnInto(tvShowDetailResponse: TvShowDetailResponse): TvShowDetail =
            TvShowDetail(tvShowDetailResponse.name ?: "",
                    tvShowDetailResponse.overview ?: "",
                    tvShowDetailResponse.backdrop_path ?: "",
                    tvShowDetailResponse.poster_path ?: "",
                    tvShowDetailResponse.vote_average ?: 0.0)
}