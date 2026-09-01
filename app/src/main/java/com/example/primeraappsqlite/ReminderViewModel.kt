package com.example.primeraappsqlite
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ReminderViewModel(private val dao: ReminderDao) : ViewModel() {
    // Los datos se obtienen del DAO y se exponen como LiveData
    val allReminders: LiveData<List<Reminder>> = dao.getAllReminders()
    // Métodos de la lógica de negocio que usan las operaciones del DAO
    fun insert(reminder: Reminder) = viewModelScope.launch {
        dao.insert(reminder)
    }
    fun update(reminder: Reminder) = viewModelScope.launch {
        dao.update(reminder)
    }
    fun delete(reminder: Reminder) = viewModelScope.launch {
        dao.delete(reminder)
    }
    // Una fábrica para crear el ViewModel con el DAO
    class ReminderViewModelFactory(private val dao: ReminderDao) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ReminderViewModel(dao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}