package com.chstudios.quitsmokingapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListeners()
    }

    fun setOnClickListeners(){
        badges_achieved_IV.setOnClickListener{
            val intent = Intent(this, BadgesActivity::class.java)
            startActivity(intent)
        }
        money_savings_IV.setOnClickListener{
            val intent = Intent(this, MoneySavingsActivity::class.java)
            startActivity(intent)
        }
        cig_tracker_IV.setOnClickListener{
            val intent = Intent(this, CigNumberTrackerActivity::class.java)
            startActivity(intent)
        }
        cessation_services_IV.setOnClickListener{
            val intent = Intent(this, CessationServicesActivty::class.java)
            startActivity(intent)
        }
        health_improvements_IV.setOnClickListener{
            val intent = Intent(this, HealthImprovementActivity::class.java)
            startActivity(intent)
        }
        motivational_videos_IV.setOnClickListener{
            val intent = Intent(this, MotivationalVideosActivity::class.java)
            startActivity(intent)
        }
        setup_IV.setOnClickListener{
            val intent = Intent(this, SetupActivity::class.java)
            startActivity(intent)
        }
    }
}