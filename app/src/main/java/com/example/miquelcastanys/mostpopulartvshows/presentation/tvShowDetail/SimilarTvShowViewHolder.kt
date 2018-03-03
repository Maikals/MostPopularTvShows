package com.example.miquelcastanys.mostpopulartvshows.presentation.tvShowDetail

import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseTvShowViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem


class SimilarTvShowViewHolder(view: View,
                              listener: OnListItemClickListener.Adapter) : BaseTvShowViewHolder(view, listener) {
    override fun bindView(tvShowListItem: TvShowListItem) {
        super.bindView(tvShowListItem)
        loadImage(tvShowListItem.posterImage)
    }
}