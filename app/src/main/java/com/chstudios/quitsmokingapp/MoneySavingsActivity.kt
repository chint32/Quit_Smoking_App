package com.chstudios.quitsmokingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_money_savings.*
import java.util.*

class MoneySavingsActivity : AppCompatActivity() {

    private var timeStopped = 0L
    private var cigsPerHour = 0F
    private var costPerCig = .45F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_savings)

        val sharedPrefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        cigsPerHour = sharedPrefs.getFloat("cigs_per_hour", 0F)
        timeStopped = sharedPrefs.getLong("time_stopped", 0L)
        var hoursSince = (Calendar.getInstance().timeInMillis - timeStopped) / (3.6*1000000)
        total_saved.text = "$" + String.format("%.2f", (costPerCig * cigsPerHour * hoursSince))
    }
}