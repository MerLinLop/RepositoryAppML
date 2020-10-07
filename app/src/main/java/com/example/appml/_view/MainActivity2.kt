package com.example.appml._view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import butterknife.BindView
import butterknife.ButterKnife
import com.example.appml.R
import com.example.appml._model.remote.Results
import com.example.appml._view.base.BaseActivity
import com.example.appml._view.base.BasicMethods
import com.example.appml._viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity2 :  BaseActivity(), BasicMethods {

    private lateinit var navController: NavController
    lateinit var mSearchViewModel: SearchViewModel

    @BindView(R.id.baseOverlayProgress)
    lateinit var baseOverlayProgress: FrameLayout
    @BindView(R.id.constraintLayout1Search)
    lateinit var constraintLayout1Search: ConstraintLayout
    @BindView(R.id.layoutBarraAcciones)
    lateinit var layoutBarraAcciones: ConstraintLayout
    @BindView(R.id.imageButtonHome)
    lateinit var imageButtonHome: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        ButterKnife.bind(this)

        navController = findNavController(R.id.nav_host_fragment)
        initObservables()
        init()
        initListeners()
    }


    override fun initObservables() {
        mSearchViewModel = ViewModelProviders.of(this).get(
            SearchViewModel::class.java
        )
    }

    override fun init() {
        layoutBarraAcciones.visibility = View.VISIBLE

    }

    override fun initListeners() {
        imageButtonHome.setOnClickListener {
            //Si esta en home
            val fragment: List<Fragment> = nav_host_fragment.childFragmentManager.fragments
            if (fragment.isNotEmpty()) {
                if (nav_host_fragment.childFragmentManager.fragments[0] is HomeFragment) {
                    //SI ESTOY EN HOME CIERRA APP
                } else  {
                    //   overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out)
                    NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.action_global_HomeFragment)
                }
            }
        }
        constraintLayout1Search.setOnClickListener {

            NavHostFragment.findNavController(nav_host_fragment.childFragmentManager.fragments[0]).navigate(R.id.action_global_SearchFragment)
        }
    }

    override fun onBackPressed() {
        val fragment: List<Fragment> = nav_host_fragment.childFragmentManager.fragments
        if (fragment.isNotEmpty()) {
            if (nav_host_fragment.childFragmentManager.fragments[0] is HomeFragment) {
                //SI ESTOY EN HOME CIERRA APP
                finish()
            } else  {
             //   overridePendingTransition(R.anim.anim_fade_in,R.anim.anim_fade_out)
                super.onBackPressed()
            }
        }
    }

    fun configurarBarra(
        hiddenBar: Boolean = true
    ) {
        if (hiddenBar) {
            layoutBarraAcciones.visibility = View.GONE
        } else {

            layoutBarraAcciones.visibility = View.VISIBLE
        }
    }

}