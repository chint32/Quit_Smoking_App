package com.chstudios.quitsmokingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.chstudios.quitsmokingapp.notifications.MyWork
import kotlinx.android.synthetic.main.activity_setup.*
import java.util.*
import java.util.concurrent.TimeUnit
import androidx.work.PeriodicWorkRequestBuilder

class SetupActivity : AppCompatActivity() {

    private var finalNumber = 0
    private var packMultiplier = 1
    private var timeMultiplier = 1
    private var cigarettesPerHour = 0F
    private var receiveNotifications = ""
    private lateinit var request: PeriodicWorkRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        val sharedPrefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

        val numberSpinner: Spinner = findViewById(R.id.numbers_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.numbers_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            numberSpinner.adapter = adapter
        }

        val numbers = resources.getStringArray(R.array.numbers_array)
        numberSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                finalNumber = numbers[position].toInt()
                Toast.makeText(
                    this@SetupActivity,
                    "You've selected: " + numbers[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }





        val packsOrCigsSpinner: Spinner = findViewById(R.id.packs_or_cigs_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.packs_or_cigs,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            packsOrCigsSpinner.adapter = adapter
        }

        val packsOrCigs = resources.getStringArray(R.array.packs_or_cigs)
        packsOrCigsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if(packsOrCigs[position] == "packs")
                    packMultiplier = 20
                else packMultiplier = 1
                Toast.makeText(
                    this@SetupActivity,
                    "You've selected: " + packsOrCigs[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }



        val unitOfTimeSpinner: Spinner = findViewById(R.id.unit_of_time_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.unit_of_time,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            unitOfTimeSpinner.adapter = adapter
        }

        val unitsOfTime = resources.getStringArray(R.array.unit_of_time)
        unitOfTimeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if(unitsOfTime[position] == "hour")
                    timeMultiplier = 1
                else if(unitsOfTime[position] == "days")
                    timeMultiplier = 24
                else if(unitsOfTime[position] == "weeks")
                    timeMultiplier = 24 * 7
                else timeMultiplier = 24 * 30
                Toast.makeText(
                    this@SetupActivity,
                    "You've selected: " + unitsOfTime[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val notificationSpinner: Spinner = findViewById(R.id.notification_spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.notifications_yes_no,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            notificationSpinner.adapter = adapter
        }

        val notifications = resources.getStringArray(R.array.notifications_yes_no)
        notificationSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                receiveNotifications = notifications[position]
                Toast.makeText(
                    this@SetupActivity,
                    "You've selected: " + notifications[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        save_button.setOnClickListener{

            if(receiveNotifications == "yes")
                scheduleDailyNotification()
            else  WorkManager.getInstance(this).cancelAllWork()



            if(packMultiplier == 20)
                cigarettesPerHour = (finalNumber * packMultiplier / timeMultiplier).toFloat()
            else cigarettesPerHour = (finalNumber / timeMultiplier).toFloat()

            val timeStopped = Calendar.getInstance().timeInMillis

            sharedPrefs.edit().putLong("time_stopped", timeStopped).apply()
            sharedPrefs.edit().putFloat("cigs_per_hour", cigarettesPerHour).apply()

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun scheduleDailyNotification(){
        Toast.makeText(this, "Notification scheduled", Toast.LENGTH_SHORT).show()
        request = PeriodicWorkRequestBuilder<MyWork>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueue(request)
    }
}