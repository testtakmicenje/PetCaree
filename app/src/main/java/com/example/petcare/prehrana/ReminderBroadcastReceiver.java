package com.example.petcare.prehrana;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import com.example.petcare.R;

public class ReminderBroadcastReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "PetCareChannel";
    private static final int NOTIFICATION_ID = 123;

    @Override
    public void onReceive(Context context, Intent intent) {
        String imeLjubimca = intent.getStringExtra("imeLjubimca");

        // Prikazivanje notifikacije
        showNotification(context, imeLjubimca);
    }

    private void showNotification(Context context, String imeLjubimca) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Kreiranje kanala za notifikacije (potrebno samo za verzije Androida 8.0 i novije)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "PetCare Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        // Kreiranje notifikacije
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.pas) // Promenite ovo prema vašem stvarnom resursu ikone
                .setContentTitle("Podsjetnik za hranjenje")
                .setContentText("Vreme za hranjenje ljubimca: " + imeLjubimca)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Notification notification = builder.build();

        // Prikazivanje notifikacije
        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
