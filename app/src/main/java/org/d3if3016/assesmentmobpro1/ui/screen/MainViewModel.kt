package org.d3if3016.assesmentmobpro1.ui.screen

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.d3if3016.assesmentmobpro1.model.Tanaman
import org.d3if3016.assesmentmobpro1.network.ApiStatus
import org.d3if3016.assesmentmobpro1.network.TanamanApi

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Tanaman>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = TanamanApi.service.getTanaman()
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}