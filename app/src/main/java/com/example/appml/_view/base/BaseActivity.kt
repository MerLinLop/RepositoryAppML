package com.example.appml._view.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appml.BuildConfig
import com.example.appml.R

abstract  class BaseActivity : AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName
    private var currentFragment: Fragment? = null
    var progressBarDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OPEN ACTIVITY $TAG")
        super.onCreate(savedInstanceState)
    }
    protected open fun replaceFragment(
        fragment: Fragment,
        layoutContainer: Int,
        addStack: Boolean
    ) {
        if (fragment.isVisible) {
            if (BuildConfig.DEBUG) Log.d(TAG, "El fragmento ya se encuentra visible")
            return
        }
        val obj = supportFragmentManager
            .beginTransaction().setCustomAnimations(R.anim.anim_fade_in, R.anim.anim_fade_out)
            .replace(layoutContainer, fragment)
        if (addStack) obj.addToBackStack(fragment.javaClass.simpleName)
        obj.commit()
        currentFragment = fragment
    }
}