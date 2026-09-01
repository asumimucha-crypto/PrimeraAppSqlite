package com.example.primeraappsqlite
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.w3c.dom.Text

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val text: String,
    val isCompleted: Boolean
)
