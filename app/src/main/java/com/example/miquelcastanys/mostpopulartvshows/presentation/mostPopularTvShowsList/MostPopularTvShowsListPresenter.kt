package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListPresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.MostPopularTvShowsListUseCase


class MostPopularTvShowsListPresenter : BaseTvShowListPresenter() {

    override fun getTvShowsList() {
        repository.let {
            MostPopularTvShowsListUseCase(it!!)
                    .getAsync("98d3f21f52adf59ccbf65cb76683d73b",
                            "en-US",
                            currentPage++,
                            useCaseCallback)
        }
    }

}