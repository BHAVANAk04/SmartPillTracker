package com.example.smartpill.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.smartpill.R
import com.example.smartpill.data.Pill
import com.example.smartpill.repo.PillRepository
import kotlinx.android.synthetic.main.activity_add_pill.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddPillActivity: AppCompatActivity() {
    private lateinit var repo: PillRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pill)
        repo = PillRepository(applicationContext)

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val dose = etDose.text.toString().trim()
            val time = etTime.text.toString().trim()
            val stock = etStock.text.toString().toIntOrNull() ?: 0
            val expiry = etExpiry.text.toString().trim()
            if(name.isEmpty() || time.isEmpty()) return@setOnClickListener

            val pill = Pill(name = name, dose = dose, timeOfDay = time, stock = stock, expiryIso = expiry)
            lifecycleScope.launch {
                val id = repo.insert(pill)
                scheduleAlarmForPill(applicationContext, id, time, name, dose)
                finish()
            }
        }
    }

    private fun scheduleAlarmForPill(context: Context, id: Long, time: String, name: String, dose: String){
        try {
            val parts = time.split(":").map { it.toInt() }
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, parts[0])
                set(Calendar.MINUTE, parts[1])
                set(Calendar.SECOND, 0)
                if(before(Calendar.getInstance())) add(Calendar.DATE, 1)
            }
            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context, com.example.smartpill.alarms.AlarmReceiver::class.java).apply {
                putExtra("pillName", name)
                putExtra("pillDose", dose)
            }
            val pi = PendingIntent.getBroadcast(context, id.toInt(), intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            am.setRepeating(AlarmManager.RTC_WAKEUP, cal.timeInMillis, AlarmManager.INTERVAL_DAY, pi)
        } catch (e: Exception){ e.printStackTrace() }
    }
}
