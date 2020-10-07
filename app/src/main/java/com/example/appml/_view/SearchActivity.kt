package com.example.appml._view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.appml.MainActivity
import com.example.appml.R
import com.example.appml._view.base.BaseActivity
import com.example.appml._view.base.BasicMethods

class SearchActivity : BaseActivity(), BasicMethods {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        setContentView(R.layout.activity_search)
        initObservables()
        init()
        initListeners()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out)
        finish()
    }
    override fun initObservables() {
    }

    override fun init() {

    }

    override fun initListeners() {

        //     volver_atras.setOnClickListener { onBackPressed() }
    }


}
