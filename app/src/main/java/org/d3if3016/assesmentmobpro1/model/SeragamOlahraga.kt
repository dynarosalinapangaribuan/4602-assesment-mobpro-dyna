package org.d3if3016.assesmentmobpro1.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "seragamOlahraga")
data class SeragamOlahraga(
    @PrimaryKey(autoGenerate = true)
    val id: Long =0L,
    val namaPemesan: String,
    val nomorTelepon: String,
    val alamatPemesan: String,
    val ukuran: String,
    val jumlahPesanan: String,
    val tanggal: String
)
