package com.example.miquelcastanys.mostpopulartvshows.presentation.interfaces


interface OnListItemClickListener {
    interface Adapter {
        fun onItemClick(position: Int, view: android.view.View)
    }
    interface View {
        fun onItemClick(position: Int, view: android.view.View)
    }
}