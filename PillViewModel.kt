package com.example.smartpill.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.smartpill.data.Pill
import com.example.smartpill.repo.PillRepository

class PillViewModel(application: Application): AndroidViewModel(application) {
    private val repo = PillRepository(application.applicationContext)
    val pills: LiveData<List<Pill>> = repo.all()

    class Factory(private val app: Application): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PillViewModel(app) as T
        }
    }
}
