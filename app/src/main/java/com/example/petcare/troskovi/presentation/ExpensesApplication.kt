package com.example.petcare.troskovi.presentation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.petcare.R
import com.example.petcare.troskovi.data.transaction.TransactionRoomDatabase
import com.example.petcare.troskovi.domain.notification.ReminderNotificationService

class ExpensesApplication : Application() {
    val database: TransactionRoomDatabase by lazy { TransactionRoomDatabase.getDatabase(this)}


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            ReminderNotificationService.REMINDER_CHANNEL_ID,
            getString(R.string.notification_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = getString(R.string.notification_desction)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}
