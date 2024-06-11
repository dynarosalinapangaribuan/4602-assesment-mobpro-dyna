package org.d3if3016.assesmentmobpro1.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3016.assesmentmobpro1.model.Tanaman
import org.d3if3016.assesmentmobpro1.network.TanamanApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Tanaman>())
        private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data.value = TanamanApi.service.getTanaman()
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}