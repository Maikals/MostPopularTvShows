package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem


class TvShowViewHolder(view: View,
                       listener: OnListItemClickListener.Adapter) : BaseTvShowViewHolder(view, listener) {
    override fun bindView(tvShowListItem: TvShowListItem) {
        super.bindView(tvShowListItem)
        loadImage(tvShowListItem.backdropImage)
    }

}