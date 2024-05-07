package org.d3if3016.assesmentmobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3016.assesmentmobpro1.database.SeragamOlahragaDao
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: SeragamOlahragaDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(
        namaPemesan: String,
        nomorTelepon: String,
        alamatPemesan: String,
        ukuran: String,
        jumlahPesanan: String
    ) {
        val seragamOlahraga = SeragamOlahraga(
            namaPemesan = namaPemesan,
            nomorTelepon = nomorTelepon,
            alamatPemesan = alamatPemesan,
            ukuran = ukuran,
            jumlahPesanan = jumlahPesanan,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(seragamOlahraga)
        }
    }

    suspend fun getSeragamOlahraga(id: Long): SeragamOlahraga? {
        return dao.getSeragamOlahragaById(id)
    }

    fun update(id: Long, namaPemesan: String, nomorTelepon: String, alamatPemesan: String, ukuran: String, jumlahPesanan: String) {
        val seragamOlahraga = SeragamOlahraga(
            id = id,
            namaPemesan = namaPemesan,
            nomorTelepon = nomorTelepon,
            alamatPemesan = alamatPemesan,
            ukuran = ukuran,
            jumlahPesanan = jumlahPesanan,
            tanggal = formatter.format(Date())
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(seragamOlahraga)
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}