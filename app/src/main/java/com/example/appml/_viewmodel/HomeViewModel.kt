package com.example.appml._viewmodel

import android.app.Application
import kotlin.random.Random

class HomeViewModel(application: Application) : BaseViewModel(application) {

    fun getDestacados(): MutableList<String> {
        var dummyList = mutableListOf<String>()

        dummyList.add("https://http2.mlstatic.com/optimize/o:f_webp/resources/deals/exhibitors_resources/mla-home-desktop-slider-picture-89501ff7-a5c6-48c4-acfa-7093823c7c4a.jpg" )
        dummyList.add("https://http2.mlstatic.com/optimize/o:f_webp/resources/exhibitors/MLA-encontra-tu-proximo-hogar/7885d000-04d6-11eb-85d9-89de7d296aeb-home-slider_desktop.jpg" )
        dummyList.add("https://http2.mlstatic.com/optimize/o:f_webp/resources/deals/exhibitors_resources/mla-home-desktop-slider-picture-a8ee0802-249b-4107-aa91-0a47cd792ac2.jpg")
        dummyList.add("https://http2.mlstatic.com/optimize/o:f_webp/resources/deals/exhibitors_resources/mla-home-desktop-slider-picture-497bcf5c-0011-4775-8a4a-2f4708e5ce0e.jpg")

        return dummyList
    }
}
