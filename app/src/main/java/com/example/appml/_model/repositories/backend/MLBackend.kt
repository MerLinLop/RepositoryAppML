package com.example.appml._model.repositories.backend

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.appml._model.networking.MLApi
import com.example.appml._model.remote.Results
import com.example.appml._model.remote.Search
import com.example.appml._model.remote._base.ErrorType
import com.example.appml._model.remote._base.OnResponse
import com.example.appml._model.remote._base.RemoteErrorEmitter
import com.example.appml._model.remote._base.ServiceGenerator
import com.example.appml._model.repositories._base.BaseRemoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext

class MLBackend(application: Application):  CoroutineScope, BaseRemoteRepository() {

    var mContext: Context = application

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private var apiService: MLApi = ServiceGenerator.createService(
        BASE_URL, null, null, MLApi::class.java
    )

    companion object {
        val TAG = MLBackend.javaClass.simpleName
    }
    suspend fun getSearchApi(
        site_id: String,
        params: Map<String, String>?,
        remoteErrorEmitter: RemoteErrorEmitter
    ): Search? {
        return safeApiCall(remoteErrorEmitter) {
            apiService.getSearchApi(site_id,params)
        }
    }

    suspend fun syncSearchProduct(site_id: String,params: Map<String, String>?, onResponse: OnResponse<Results>) {
        var auxLisr: List<Results> = mutableListOf()
        getSearchApi(site_id,params, object : RemoteErrorEmitter {
            override fun onError(msg: String) {
                Log.e(TAG, "error:  $msg")
                onResponse.onError(0, msg)
            }
            override fun onError(errorType: ErrorType) {
                Log.e(TAG, "error:  $errorType.name")
                onResponse.onError(0, errorType.name)
            }
        })?.let { list ->
            Log.d(TAG, "BUSQUEDA PRODUCTO:  $list")
            if(!list.results.isNullOrEmpty()){
                auxLisr= list.results!!
            }
        }
        onResponse.onResponse(OnResponse.ResponseType.OK, null, auxLisr)
    }
}