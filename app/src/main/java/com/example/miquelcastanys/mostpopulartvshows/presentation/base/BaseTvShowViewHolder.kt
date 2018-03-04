package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.glideModule.GlideApp
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.util.PresentationConstants
import kotlinx.android.synthetic.main.similar_tv_show_list_item.view.*


abstract class BaseTvShowViewHolder(val view: View,
                                    private val listener: OnListItemClickListener.Adapter) : RecyclerView.ViewHolder(view) {

    open fun bindView(tvShowListItem: TvShowListItem) {
        view.tvShowTitle.text = tvShowListItem.title
        val ratingString = "${view.context.getString(R.string.rating)} ${tvShowListItem.voteAverage}"
        view.tvShowRating.text = ratingString
        view.setOnClickListener { listener.onItemClick(adapterPosition, view.tvShowImage) }
        loadImage(tvShowListItem.backdropImage)
    }

    protected fun loadImage(imageUrl: String) {
        GlideApp
                .with(view)
                .load("${PresentationConstants.BASE_IMAGE_URL}$imageUrl")
                .placeholder(R.drawable.ic_placeholder_white)
                .centerCrop()
                .into(view.tvShowImage)
    }
}