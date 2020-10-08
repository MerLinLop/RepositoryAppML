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
import com.example.appml._model.remote.product.DescriptionsProduct
import com.example.appml._model.remote.product.ProductServer
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


    ///API GET PRODUCTOS///////////////////
    suspend fun getSearchApi(
        site_id: String,
        params: Map<String, String>?,
        remoteErrorEmitter: RemoteErrorEmitter
    ): Search? {
        return safeApiCall(remoteErrorEmitter) {
            apiService.getSearchApi(site_id,params)
        }
    }
    ///GET PRODUCTOS COINCIDAN CON LA BUSQUEDA///////////////////
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

    ///API GET DETALLE PRODUCTO///////////////////
    suspend fun getProductApi(
        params: Map<String, String>?,
        remoteErrorEmitter: RemoteErrorEmitter
    ): List<ProductServer>? {
        return safeApiCall(remoteErrorEmitter) {
            apiService.getProductApi(params)
        }
    }
    ///GET DETALLE PRODUCTO (ATRIBUTOS Y FOTOS))///////////////////
    suspend fun syncProduct(params: Map<String, String>?, onResponse: OnResponse<ProductServer>) {
        var auxLisr: List<ProductServer> = mutableListOf()
        getProductApi(params, object : RemoteErrorEmitter {
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
                auxLisr= list

        }
        onResponse.onResponse(OnResponse.ResponseType.OK,null ,auxLisr )
    }



    ///API GET DETALLE DESCRIPCION PRODUCTO///////////////////
    suspend fun getProductDescriptApi(
        item_id: String,
        remoteErrorEmitter: RemoteErrorEmitter
    ): List<DescriptionsProduct>? {
        return safeApiCall(remoteErrorEmitter) {
            apiService.getProductDescriptApi(item_id)
        }
    }
    ///GET DETALLE DESCRIPCION PRODUCTO (DESCRIPCION))///////////////////
    suspend fun syncDescriptionProduct(item_id: String, onResponse: OnResponse<DescriptionsProduct>) {
        var auxLisr: List<DescriptionsProduct> = mutableListOf()
        getProductDescriptApi(item_id, object : RemoteErrorEmitter {
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
            auxLisr= list

        }
        onResponse.onResponse(OnResponse.ResponseType.OK, null,auxLisr )
    }
}