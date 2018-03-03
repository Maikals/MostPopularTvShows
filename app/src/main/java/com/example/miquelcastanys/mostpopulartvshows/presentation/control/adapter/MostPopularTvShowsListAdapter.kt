package com.example.miquelcastanys.mostpopulartvshows.presentation.control.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.base.BaseListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.genericHolders.FooterViewHolder
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowViewHolder
import android.view.animation.AnimationUtils
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener


class MostPopularTvShowsListAdapter(private val tvShowList: List<BaseListItem>,
                                    val listener: OnListItemClickListener.View) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        OnListItemClickListener.Adapter{

    private var lastPosition: Int = -1

    companion object {
        private const val TV_SHOW_TYPE: Int = 0
        private const val FOOTER_TYPE: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TV_SHOW_TYPE -> TvShowViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.tv_show_list_item, parent, false), this)
                else -> FooterViewHolder(LayoutInflater.from(parent?.context)
                        .inflate(R.layout.footer_list_item, parent, false))
            }


    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as? TvShowViewHolder)?.bindView((tvShowList[position] as? TvShowListItem)!!)
        if ( (holder as? TvShowViewHolder)?.view != null)
            setAnimation((holder as? TvShowViewHolder)?.view!!, position)
    }


    override fun getItemViewType(position: Int): Int =
            if (tvShowList[position] is TvShowListItem) TV_SHOW_TYPE
            else FOOTER_TYPE

    fun restartLastPosition() {
        lastPosition = -1
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun onItemClick(position: Int, view: View) {
        listener.onItemClick(position, view)
    }
}