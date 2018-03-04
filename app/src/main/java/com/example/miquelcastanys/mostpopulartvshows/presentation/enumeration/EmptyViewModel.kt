package com.example.miquelcastanys.mostpopulartvshows.presentation.enumeration

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.example.miquelcastanys.mostpopulartvshows.R

enum class EmptyViewModel(@DrawableRes val imageId: Int, @StringRes val title: Int, @StringRes val subtitle: Int) {
    ERROR(R.drawable.ic_empty_view, R.string.error_empty_view_title, R.string.error_empty_view_description),
    EMPTY(R.drawable.ic_empty_view, R.string.no_elements_empty_view_title, R.string.no_elements_empty_view_description)
}