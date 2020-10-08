package com.example.appml._view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.appml.R
import com.example.appml._view.adapters.DestacadoViewPagerAdapter
import com.example.appml._view.base.BaseFragment
import com.example.appml._view.base.BasicMethods
import com.example.appml._viewmodel.HomeViewModel
import me.relex.circleindicator.CircleIndicator

class HomeFragment : BaseFragment(), BasicMethods {


    lateinit var homeFragmentView: View
    lateinit var viewPagerDestacado: ViewPager
    lateinit var circleIndicator: CircleIndicator
    private lateinit var mContext: Context
    lateinit var adapterDestacado: DestacadoViewPagerAdapter
    lateinit var mHomeViewModel: HomeViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeFragmentView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        initObservables()
        initListeners()
    }

    override fun initObservables() {
        mHomeViewModel = ViewModelProviders.of(this).get(
            HomeViewModel::class.java
        )

        adapterDestacado = DestacadoViewPagerAdapter()
        viewPagerDestacado.adapter = adapterDestacado
        circleIndicator.setViewPager(viewPagerDestacado)
        adapterDestacado.registerDataSetObserver(circleIndicator.dataSetObserver)
        adapterDestacado.add(mHomeViewModel.getDestacados())
    }

    override fun init() {

        viewPagerDestacado = homeFragmentView.findViewById(R.id.viewPagerDestacado)
        circleIndicator = homeFragmentView.findViewById(R.id.circleIndicator)
        (getActivity() as MainActivity2).setSearch("",true)



    }

    override fun initListeners() {

    }


}