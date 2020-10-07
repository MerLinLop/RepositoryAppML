package com.example.appml._model.local

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appml._model.local.historic_search.HistoricSearchDao
import com.example.appml._model.local.historic_search.HistoricSearchEntity

@Database(
    entities = [
        HistoricSearchEntity::class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun historicSearchDao(): HistoricSearchDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(
                        context
                    ).also {
                        INSTANCE = it
                    }
            }

        private fun buildDatabase(context: Context) =
            databaseBuilder(
                context,
                AppDatabase::class.java, "appml.db"
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d("ONCREATE", "Database has been created.")

                    }

                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Log.d("ONOPEN", "Database has been opened.")
                    }
                })
                .fallbackToDestructiveMigration()
                .build()
    }
}