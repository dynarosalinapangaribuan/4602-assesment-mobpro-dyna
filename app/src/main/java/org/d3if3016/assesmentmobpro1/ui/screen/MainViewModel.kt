package org.d3if3016.assesmentmobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga

class MainViewModel : ViewModel() {

    val data = getDataDummy()

    private fun getDataDummy(): List<SeragamOlahraga> {
        val namaPemesan = arrayOf(
            "Chaeza Fauziyah Innayah",
            "Feby Irmayana",
            "Adelia Putri Abadi",
            "Syfanadya Wening Adi",
            "Mariana",
            "Dyna Rosalina Pangaribuan"
        )
        val nomorTelepon = arrayOf(
            "081322345678",
            "085222345679",
            "083320945668",
            "082127145968",
            "081236345968",
            "082278970834"
        )
        val alamatPemesan = arrayOf(
            "Sukabirus",
            "Sukapura",
            "Ciganitri",
            "Marga Cinta",
            "Batununggal",
            "Lembang"
        )
        val ukuran = arrayOf(
            "S",
            "M",
            "L",
            "XL",
            "XXL",
            "XXXL"
        )
        val jumlahPesanan = arrayOf(
            "35",
            "22",
            "156",
            "200",
            "231",
            "123"
        )
        val tanggal = arrayOf(
            "25-01-2023",
            "24-03-2023",
            "16-06-2023",
            "26-06-2023",
            "01-03-2024",
            "030-03-2024"
        )

        val data = mutableListOf<SeragamOlahraga>()
        for (i in namaPemesan.indices) {
            data.add(
                SeragamOlahraga(
                    (i + 1).toLong(), // Menambah 1 karena indeks dimulai dari 0
                    namaPemesan[i],
                    nomorTelepon[i],
                    alamatPemesan[i],
                    ukuran[i],
                    jumlahPesanan[i],
                    tanggal[i]
                )
            )
        }
        data.sortBy { it.tanggal }

        return data
    }
}