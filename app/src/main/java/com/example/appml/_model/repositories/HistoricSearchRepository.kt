package com.example.appml._model.repositories

import android.app.Application
import androidx.annotation.WorkerThread
import com.example.appml._model.local.AppDatabase
import com.example.appml._model.local.historic_search.HistoricSearchEntity
import com.example.appml.utils.ResponseObjetBasic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class HistoricSearchRepository (app: Application): CoroutineScope {

    var mDatabase = AppDatabase.getInstance(app)
    var historicSearchDao = mDatabase.historicSearchDao()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    @WorkerThread
    suspend fun insert(historic:HistoricSearchEntity) = withContext(Dispatchers.IO){
        historicSearchDao.insertHistoric(historic)
    }
    @WorkerThread
    suspend fun getAllHistoric(responseObjetBasic: ResponseObjetBasic<List<HistoricSearchEntity>>)= withContext(Dispatchers.IO){
        val historic=historicSearchDao.getAllHistoric()
        responseObjetBasic.onSuccess(historic)
    }
    @WorkerThread
    suspend fun deleteHistoric(dominio: String) = withContext(Dispatchers.IO) {
        historicSearchDao.deleteHitoric(dominio)
    }

}