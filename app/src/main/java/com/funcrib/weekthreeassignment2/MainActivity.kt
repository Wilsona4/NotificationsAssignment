package com.funcrib.weekthreeassignment2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "WIL"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*Create Notification Channel*/
        createNotificationChannel()

        /*create notification onClick of button*/
        btNotification.setOnClickListener {
            addNotification()
        }

        /*Move to Secondary Activity and send extra*/
        btSecActivity.setOnClickListener {
            val intent = Intent(this, SecondaryActivity::class.java).apply {
                putExtra("INACTIVE", "INACTIVE")
            }
            startActivity(intent)
        }
    }

    /*Method to Create Notification*/
    private fun addNotification() {
        // Create an explicit intent for an Activity in your app
        val intent = Intent(this, SecondaryActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("ACTIVE", "ACTIVE")
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        /*Notification Builder*/
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_bed_time)
            .setContentTitle("Bed Time")
            .setContentText("It's Bed Time. Remember Early to Bed, Early to Rise")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, builder.build())
        }
    }

    /*Create the NotificationChannel*/
    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}