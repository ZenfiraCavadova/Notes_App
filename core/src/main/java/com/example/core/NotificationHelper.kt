package com.example.core

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat.getSystemService
import android.os.Build
import androidx.core.app.NotificationCompat

object NotificationHelper {

    private const val CHANNEL_ID="Note Channel ID"
     fun showNotification(context: Context) {

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_note)
            .setContentTitle("Note Saved!")
            .setContentText("You saved a note")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        var notificationManager:NotificationManager? =null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Message"
            val descriptionText = "Note Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
                channel.description = descriptionText
                notificationManager = getSystemService(context,NotificationManager::class.java)
                notificationManager?.createNotificationChannel(channel)
                builder.setChannelId(CHANNEL_ID)
        }
        notificationManager?.notify(1234, builder.build())
    }
}