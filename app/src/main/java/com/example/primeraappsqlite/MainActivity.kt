package com.example.primeraappsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ReminderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Obtener la base de datos y el DAO

        val database = ReminderDatabase.getDatabase(this)
        val dao = database.reminderDao()

        //Inicialaizar el ViewModel usando la fabrica
        val factory = ReminderViewModel.ReminderViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(ReminderViewModel::class.java)
        // Ejemplo de uso: insertar un recordatorio al iniciar la app
        viewModel.insert(Reminder(text = "Comprar leche", isCompleted = false))
        // Observar los datos para actualizar la UI
        viewModel.allReminders.observe(this) { reminders ->
            // Aquí puedes actualizar tu RecyclerView, TextView, etc.
            // Por ahora, solo lo mostraremos en el logcat
            reminders.forEach {
                println("Recordatorio: ${it.text} (Completado: ${it.isCompleted})")
            }
        }
    }
}

