package org.d3if3016.assesmentmobpro1.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga

@Dao
interface SeragamOlahragaDao {
    @Insert
    suspend fun insert(seragamOlahraga: SeragamOlahraga)

    @Update
    suspend fun update(seragamOlahraga: SeragamOlahraga)

    @Query("SELECT * FROM seragamOlahraga ORDER BY tanggal DESC")
    fun getSeragamOlahraga(): Flow<List<SeragamOlahraga>>

    @Query("SELECT * FROM seragamOlahraga WHERE id = :id")
    suspend fun getSeragamOlahragaById(id: Long): SeragamOlahraga?
}