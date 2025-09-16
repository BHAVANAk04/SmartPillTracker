package com.example.smartpill.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.smartpill.util.NotificationHelper

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val name = intent.getStringExtra("pillName") ?: "Pill"
        val dose = intent.getStringExtra("pillDose") ?: "Dose"
        NotificationHelper.showNotification(context, "Time to take $name", dose)
    }
}
