package com.example.miquelcastanys.mostpopulartvshows.presentation.model.mappers

import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.MostPopularTvShowListResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.domain.TvShowResponse
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem

object MostPopularTvShowsListMapper {
    fun turnInto(mostPopularTvShowListResponse: MostPopularTvShowListResponse): ArrayList<BaseListItem> =
            createTvShowList(mostPopularTvShowListResponse.results)

    private fun createTvShowList(results: List<TvShowResponse>): ArrayList<BaseListItem> {
        val tvShowList = ArrayList<BaseListItem>()
        results.forEach {
            tvShowList.add(TvShowListItem(it.id ?: 0, it.name ?: "", it.vote_average
                    ?: 0.0, it.backdrop_path ?: ""))
        }
        return tvShowList
    }
}