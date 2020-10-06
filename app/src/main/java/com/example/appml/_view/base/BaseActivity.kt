package com.example.appml._view.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract  class BaseActivity : AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName
    private var currentFragment: Fragment? = null
    var progressBarDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "OPEN ACTIVITY $TAG")
        super.onCreate(savedInstanceState)
    }

}