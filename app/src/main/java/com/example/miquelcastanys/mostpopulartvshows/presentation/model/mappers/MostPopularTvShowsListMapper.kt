package com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers

import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem

object MostPopularTvShowsListMapper {
    fun turnInto(mostPopularTvShowListResponse: MostPopularTvShowListResponse) : ArrayList<TvShowListItem> =
        createTvShowList(mostPopularTvShowListResponse.results)

    private fun createTvShowList(results: List<TvShowResponse>): ArrayList<TvShowListItem> {
        val tvShowList = ArrayList<TvShowListItem>()
        results.forEach {
            tvShowList.add(TvShowListItem(it.id, it.name, it.vote_average, it.poster_path))
        }
        return tvShowList
    }
}