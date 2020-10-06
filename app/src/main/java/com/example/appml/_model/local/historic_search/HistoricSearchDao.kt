package com.example.appml._model.local.historic_search

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface HistoricSearchDao {
    @Insert()
    fun insertHistoric(historic: HistoricSearchEntity)

    @Query("SELECT * FROM _HISTORICO_BUSCADOR")
    fun getAllHistoric(): List<HistoricSearchEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateHistoric(historicEntity: HistoricSearchEntity)

    @Query("DELETE FROM _HISTORICO_BUSCADOR")
    fun deleteAllHitoric()

    @Query("DELETE FROM _HISTORICO_BUSCADOR WHERE palabra = :palabra")
    fun deleteHitoric(palabra:String)
}