package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.domain.DomainConstants
import com.example.miquelcastanys.mostpopulartvshows.presentation.glideModule.GlideApp
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import kotlinx.android.synthetic.main.tv_show_list_item.view.*


class TvShowViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(tvShowListItem: TvShowListItem) {
        view.tvShowTitle.text = tvShowListItem.title
        view.tvShowRating.text = "Rating: ${tvShowListItem.voteAverage}"
        GlideApp
                .with(view)
                .load("https://image.tmdb.org/t/p/w1280" + tvShowListItem.image)
                .placeholder(R.drawable.ic_action_name)
                .centerCrop()
                .into(view.img_gallery)
    }
}