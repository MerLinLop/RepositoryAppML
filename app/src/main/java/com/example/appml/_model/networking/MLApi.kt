package com.example.appml._model.networking

import com.example.appml._model.remote.Search
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MLApi {

    @GET("sites/{SITE_ID}/search")
     suspend fun getSearchApi(@Path("SITE_ID") site_id: String?, @QueryMap params: Map<String, String>?): Search

}