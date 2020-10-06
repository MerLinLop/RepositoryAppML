package com.example.appml._model.local.historic_search

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_HISTORICO_BUSCADOR")
data class HistoricSearchEntity (
    @PrimaryKey
    @ColumnInfo(name = "Palabra")
    var palabra: String

)
