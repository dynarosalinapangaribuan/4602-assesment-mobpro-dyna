package org.d3if3016.assesmentmobpro1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga

@Database(entities = [SeragamOlahraga::class], version = 1, exportSchema = false)
abstract class SeragamOlahragaDb : RoomDatabase() {

    abstract val dao: SeragamOlahragaDao

    companion object {
        private var INSTANCE: SeragamOlahragaDb? = null

        fun getInstance(context: Context): SeragamOlahragaDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SeragamOlahragaDb::class.java,
                        "seragamOlahraga.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}