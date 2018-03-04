package com.example.miquelcastanys.mostpopulartvshows.presentation.similarTvShowsList

import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListPresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.GetSimilarTvShowsListUseCase


class SimilarTvShowsListPresenter(val id: Int) : BaseTvShowListPresenter() {
    override fun getTvShowsList() {
        repository.let {
            GetSimilarTvShowsListUseCase(it!!)
                    .getAsync(id,
                            "98d3f21f52adf59ccbf65cb76683d73b",
                            "en-US",
                            currentPage++,
                            useCaseCallback)
        }
    }
}