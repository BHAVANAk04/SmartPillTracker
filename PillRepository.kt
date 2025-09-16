package com.example.smartpill.repo

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.smartpill.data.Pill
import com.example.smartpill.data.PillDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PillRepository(context: Context) {
    private val dao = PillDatabase.getDatabase(context).pillDao()
    fun all(): LiveData<List<Pill>> = dao.getAll()
    suspend fun insert(p: Pill) = withContext(Dispatchers.IO){ dao.insert(p) }
    suspend fun update(p: Pill) = withContext(Dispatchers.IO){ dao.update(p) }
    suspend fun delete(p: Pill) = withContext(Dispatchers.IO){ dao.delete(p) }
}
