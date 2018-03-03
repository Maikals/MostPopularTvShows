package com.example.miquelcastanys.mostpopulartvshows.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.miquelcastanys.mostpopulartvshows.R

abstract class BaseActivity : AppCompatActivity() {

    var currentTag: String? = null
    var currentFragment: Fragment? = null

    companion object {
        private const val CURRENT_FRAGMENT_TAG: String = "currentTag"
    }

    protected fun initializeFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(currentTag)) {
            createFragment()
        } else {
            currentTag = savedInstanceState.getString(CURRENT_FRAGMENT_TAG)
            currentFragment = supportFragmentManager.getFragment(savedInstanceState, currentTag)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        supportFragmentManager.putFragment(outState, currentTag, currentFragment)
        outState?.putString(CURRENT_FRAGMENT_TAG, currentTag)
        super.onSaveInstanceState(outState)
    }

    protected fun setFragment() =
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, currentFragment, currentTag).commit()

    abstract fun createFragment()
}