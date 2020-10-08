package com.example.appml._view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
    @BindView(R.id.textViewSearch)
    lateinit var textViewSearch: TextView

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
            goHome()

        }
        constraintLayout1Search.setOnClickListener {
            goSearch()
        }
    }
    fun goHome() {
        val fragment: List<Fragment> = nav_host_fragment.childFragmentManager.fragments
        if (fragment.isNotEmpty()) {
            if (nav_host_fragment.childFragmentManager.fragments[0] is HomeFragment) {
                //SI ESTOY EN HOME QUEDA DONDE ESTA
            } else  {
                //SI ESTOY EN OTRO FRAGMENT VUELVE AL HOME
                NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.action_global_HomeFragment)
                setSearch("",true)
            }
        }

    }
    fun goSearch() {
        try {
            NavHostFragment.findNavController(nav_host_fragment.childFragmentManager.fragments[0])
                .navigate(
                    R.id.action_global_SearchFragment
                )

        } catch (e: Exception) {
            e.message?.let { Log.e(TAG, it) }
        }
        /*notificationsOpen = if (mainNavHost.childFragmentManager.fragments.size > 0) {
            NavHostFragment.findNavController(mainNavHost.childFragmentManager.fragments[0])
                .navigate(R.id.action_global_HomeFragment)
            init()
            false
        } else {
            NavHostFragment.findNavController(mainNavHost).navigate(R.id.action_global_HomeFragment)
            init()
            false
        }*/

    }
    override fun onBackPressed() {
        val fragment: List<Fragment> = nav_host_fragment.childFragmentManager.fragments
        if (fragment.isNotEmpty()) {
            if (nav_host_fragment.childFragmentManager.fragments[0] is HomeFragment) {
                //SI ESTOY EN HOME CIERRA APP
                finish()
            } else  if (nav_host_fragment.childFragmentManager.fragments[0] is ProductsFragment){
                //SI ESTOY EN ProductsFragment VUELVE AL HOME
                NavHostFragment.findNavController(nav_host_fragment).navigate(R.id.action_global_HomeFragment)
            }else {
                //SI ESTOY EN OTRO FRAGMENT VUELVE AL ANTERIOR
                super.onBackPressed()
                init()
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
    fun setSearch(
        word: String, hint:Boolean
    ) {
        textViewSearch.text=word
        if(hint){
            textViewSearch.hint="Buscar..."
        }
    }

}