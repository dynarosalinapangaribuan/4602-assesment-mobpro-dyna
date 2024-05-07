package org.d3if3016.assesmentmobpro1.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3016.assesmentmobpro1.database.SeragamOlahragaDao
import org.d3if3016.assesmentmobpro1.model.SeragamOlahraga

class MainViewModel(dao: SeragamOlahragaDao) : ViewModel() {

    val data: StateFlow<List<SeragamOlahraga>> = dao.getSeragamOlahraga().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )
}