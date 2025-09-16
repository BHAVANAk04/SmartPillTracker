package com.example.smartpill.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Re-schedule alarms after reboot.
        // For brevity this example does not re-query DB. Production should re-schedule each pill alarm here.
    }
}
