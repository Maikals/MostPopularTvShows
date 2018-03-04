package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces.OnListItemClickListener
import com.example.miquelcastanys.mostpopulartvshows.presentation.model.presentation.TvShowListItem
import com.example.miquelcastanys.mostpopulartvshows.presentation.mostPopularTvShowsList.TvShowViewHolder


abstract class BaseTvShowListAdapter(private val tvShowList: List<BaseListItem>,
                                     private val listener: OnListItemClickListener.View)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
        OnListItemClickListener.Adapter {

    private var lastPosition: Int = -1

    companion object {
        private const val TV_SHOW_TYPE: Int = 0
        private const val FOOTER_TYPE: Int = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TV_SHOW_TYPE -> createTvShowViewHolder(parent)
                else -> createFooterViewHolder(parent)
            }


    abstract fun createTvShowViewHolder(parent: ViewGroup?): BaseTvShowViewHolder

    abstract fun createFooterViewHolder(parent: ViewGroup?): BaseFooterViewHolder

    override fun getItemCount(): Int = tvShowList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (holder is BaseTvShowViewHolder)
            bindView(holder, (tvShowList[position] as? TvShowListItem)!!)

        if ((holder as? BaseTvShowViewHolder)?.view != null)
            setAnimation((holder as? BaseTvShowViewHolder)?.view!!, position)
    }

    abstract fun bindView(holder: BaseTvShowViewHolder, tvShowListItem: TvShowListItem)


    override fun getItemViewType(position: Int): Int =
            if (tvShowList[position] is TvShowListItem) TV_SHOW_TYPE
            else FOOTER_TYPE

    fun restartLastPosition() {
        lastPosition = -1
    }

    protected open fun setAnimation(viewToAnimate: View, position: Int) {
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