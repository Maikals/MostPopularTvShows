package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowListPresenter
import com.example.miquelcastanys.mostpopulartvshows.presentation.useCases.MostPopularTvShowsListUseCase
import com.example.miquelcastanys.mostpopulartvshows.presentation.util.PresentationConstants


class MostPopularTvShowsListPresenter : BaseTvShowListPresenter() {

    override fun getTvShowsList() {
        repository.let {
            MostPopularTvShowsListUseCase(it!!)
                    .getAsync(PresentationConstants.API_KEY,
                            PresentationConstants.LANGUAGE,
                            currentPage++,
                            useCaseCallback)
        }
    }

}