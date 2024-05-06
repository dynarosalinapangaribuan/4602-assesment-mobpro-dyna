package org.d3if3016.assesmentmobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga

class DetailViewModel : ViewModel() {
    private val mainViewModel = MainViewModel()

    fun getSeragamOlahraga(id: Long): SeragamOlahraga? {
        return mainViewModel.data.find { it.id == id }
    }
}