package com.example.appml._viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.appml._model.local.historic_search.HistoricSearchEntity
import com.example.appml._model.remote.Results
import com.example.appml._model.remote._base.OnResponse
import com.example.appml._model.remote.product.DescriptionsProduct
import com.example.appml._model.remote.product.ProductServer
import com.example.appml._model.repositories.HistoricSearchRepository
import com.example.appml._model.repositories.backend.MLBackend
import com.example.appml.utils.ResponseObjetBasic
import kotlinx.coroutines.*
import java.util.ArrayList
import java.util.HashMap
import kotlin.coroutines.CoroutineContext

class SearchViewModel(application: Application): BaseViewModel(application), CoroutineScope {

    private val TAG: String = SearchViewModel::class.java.simpleName

    private val job: Job = SupervisorJob()
    private var historic_repository = HistoricSearchRepository(application)
    var mMLBackend = MLBackend(application)

    var listHistoricSearch = MutableLiveData<List<HistoricSearchEntity>>()
    var liveDataListProducts = MutableLiveData<List<Results>>()
    var liveDataItemProducts = MutableLiveData<ProductServer>()
    var liveDataDescriptionsProduct = MutableLiveData<DescriptionsProduct>()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job // By default child coroutines will run on the main thread.



   suspend fun seachProduct(search :String ){
        val sites_id = "MLA"
        val map = HashMap<String, String>()
        map["q"] = search

        GlobalScope.launch {
            mMLBackend.syncSearchProduct(
                sites_id,
                map,
                object : OnResponse<Results> {
                    override fun onResponse(
                        responseType: OnResponse.ResponseType,
                        entity: Results?,
                        listEntity: List<Results>?
                    ) {
                        liveDataListProducts.postValue(listEntity)
                    }

                    override fun onError(code: Int, error: String?) {
                        Log.e(TAG,"ERROR TRAER PRODUCTOS $error")
                        val listEntity: List<Results> = arrayListOf()
                        liveDataListProducts.postValue(listEntity)
                    }
                })
        }

    }

    suspend fun getItemProduct(idItem :String ){
        //ids=MLA736948974 &
        //attributes=attributes,pictures,descriptions

        val map = HashMap<String, String>()
        map["ids"] = idItem
        map["attributes"] = "attributes,pictures"
        GlobalScope.launch {
            mMLBackend.syncProduct(
                map,
                object : OnResponse<ProductServer> {
                    override fun onResponse(
                        responseType: OnResponse.ResponseType,
                        entity: ProductServer?,
                        listEntity: List<ProductServer>?
                    ) {
                        if(!listEntity.isNullOrEmpty())liveDataItemProducts.postValue(listEntity[0])
                    }

                    override fun onError(code: Int, error: String?) {
                        Log.e(TAG,"ERROR TRAER PRODUCTOS $error")
                        val listEntity = ProductServer()
                        liveDataItemProducts.postValue(listEntity)
                    }
                })
        }

    }

    suspend fun getDescriptionsProduct(idItem :String ){

        GlobalScope.launch {
            mMLBackend.syncDescriptionProduct(
                idItem,
                object : OnResponse<DescriptionsProduct> {
                    override fun onResponse(
                        responseType: OnResponse.ResponseType,
                        entity: DescriptionsProduct?,
                        listEntity: List<DescriptionsProduct>?
                    ) {
                        if(!listEntity.isNullOrEmpty())liveDataDescriptionsProduct.postValue(listEntity[0])

                    }

                    override fun onError(code: Int, error: String?) {
                        Log.e(TAG,"ERROR TRAER PRODUCTOS $error")
                        val listEntity = DescriptionsProduct()
                        liveDataDescriptionsProduct.postValue(listEntity)
                    }
                })
        }

    }

    fun insertSearch(word: String) {
        launch {
            val search  = HistoricSearchEntity(word)
            historic_repository.insert(search)
        }
    }
    fun getHistoricSearchEntity(responseObjetBasic: ResponseObjetBasic<List<HistoricSearchEntity>>) {
        launch {
            historic_repository.getAllHistoric(responseObjetBasic)
        }
    }
    fun dataHistoricSearch(){
        getHistoricSearchEntity(object : ResponseObjetBasic<List<HistoricSearchEntity>> {
            override fun onSuccess(entity: List<HistoricSearchEntity>) {
                listHistoricSearch.postValue(entity)
            }

            override fun onError(message: String) {

            }
        })

    }
}