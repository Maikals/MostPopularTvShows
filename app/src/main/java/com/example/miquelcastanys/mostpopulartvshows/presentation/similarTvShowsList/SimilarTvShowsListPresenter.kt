package com.example.miquelcastanys.mostpopulartvshows.presentation.similarTvShowsList

import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListPresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.GetSimilarTvShowsListUseCase
import com.example.miquelcastanys.mostpopulartvshows.presentation.util.PresentationConstants


class SimilarTvShowsListPresenter(val id: Int) : BaseTvShowListPresenter() {
    override fun getTvShowsList() {
        repository.let {
            GetSimilarTvShowsListUseCase(it!!)
                    .getAsync(id,
                            PresentationConstants.API_KEY,
                            PresentationConstants.LANGUAGE,
                            currentPage++,
                            useCaseCallback)
        }
    }
}