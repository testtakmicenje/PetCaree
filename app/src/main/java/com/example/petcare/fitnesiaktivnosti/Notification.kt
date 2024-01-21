package com.example.petcare.fitnesiaktivnosti

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.petcare.R

class Notification : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        // Build and display the notification
        val notificationBuilder = NotificationCompat.Builder(
            context!!,
            "channelId"
        )

        notificationBuilder.apply {
            setSmallIcon(R.drawable.notificationicon)
            setContentTitle("Vaš podsjetnik")
            setContentText("Sada je vrijeme za hranjenje!")
        }

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        NotificationManagerCompat.from(context)
            .notify(1, notificationBuilder.build())
    }
}

