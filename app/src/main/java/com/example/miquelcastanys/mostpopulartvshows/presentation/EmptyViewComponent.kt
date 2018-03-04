package com.example.miquelcastanys.mostpopulartvshows.presentation

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.miquelcastanys.mostpopulartvshows.R
import com.example.miquelcastanys.mostpopulartvshows.presentation.enumeration.EmptyViewModel
import kotlinx.android.synthetic.main.generic_empty_view.view.*

class EmptyViewComponent : FrameLayout {
    private var imageView: ImageView? = null
    private var titleTv: TextView? = null
    private var textTv: TextView? = null
    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }
    private fun init() {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.generic_empty_view, this, true)
        imageView = view.genericEmptyViewIv
        titleTv = view.genericEmptyViewTitleTv
        textTv = view.genericEmptyViewTextTv
    }
    fun fillViews(emptyViewModel: EmptyViewModel) {
        imageView!!.setImageDrawable(ContextCompat.getDrawable(context, emptyViewModel.imageId))
        titleTv!!.text = context.getString(emptyViewModel.title)
        textTv!!.text = context.getString(emptyViewModel.subtitle)
    }
}
