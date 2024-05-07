package org.d3if3016.assesmentmobpro1.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3016.assesmentmobpro1.database.SeragamOlahragaDao
import org.d3if3016.assesmentmobpro1.ui.screen.DetailViewModel
import org.d3if3016.assesmentmobpro1.ui.screen.MainViewModel

class ViewModelFactory(
    private val dao: SeragamOlahragaDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(dao) as  T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}