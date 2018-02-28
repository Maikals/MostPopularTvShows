package com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import kotlinx.android.synthetic.main.tv_show_list_item.view.*


class TvShowViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun bindView(tvShowListItem: TvShowListItem) {
        view.tvShowTitle.text = tvShowListItem.title
    }
}