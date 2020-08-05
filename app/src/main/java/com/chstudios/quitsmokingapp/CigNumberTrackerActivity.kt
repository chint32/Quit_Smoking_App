package com.chstudios.quitsmokingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_cig_number_tracker.*
import java.util.*
import kotlin.collections.ArrayList


class CigNumberTrackerActivity : AppCompatActivity() {

    var barChart: BarChart? = null
    var BARENTRY: ArrayList<BarEntry>? = null
    var BarEntryLabels: ArrayList<String>? = null
    var Bardataset: BarDataSet? = null
    var BARDATA: BarData? = null
    var y0 = 3F
    var y1 = 4F
    var y2 = 0F
    var y3 = 0F
    var y4 = 0F
    var y5 = 0F
    var y6 = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cig_number_tracker)

        BarEntryLabels = ArrayList()
        AddValuesToBarEntryLabels()

        BARENTRY = ArrayList()
        AddValuesToBARENTRY()

        barChart = findViewById(R.id.tracker_bar_chart) as BarChart
        val xAxis = barChart!!.xAxis
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setValueFormatter(IndexAxisValueFormatter(BarEntryLabels))
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        Bardataset = BarDataSet(BARENTRY, "Projects")

        BARDATA = BarData(Bardataset)
        barChart!!.setData(BARDATA)

        Bardataset!!.setColors(*ColorTemplate.COLORFUL_COLORS)
        barChart!!.animateY(1500)
        barChart!!.legend.isEnabled = false

        val calendar = Calendar.getInstance()
        var day = calendar[Calendar.DAY_OF_WEEK]

        add_to_tracker_button.setOnClickListener{
            when (day) {
                Calendar.SUNDAY -> {
                    y0 += 1
                }
                Calendar.MONDAY -> {
                    y1 += 1
                }
                Calendar.TUESDAY -> {
                    y2 += 1
                }
                Calendar.WEDNESDAY -> {
                    y3 += 1
                }
                Calendar.THURSDAY -> {
                    y4 += 1
                }
                Calendar.FRIDAY -> {
                    y5 += 1
                }
                Calendar.SATURDAY -> {
                    y6 += 1
                }
            }

            var NEWBARENTRY = ArrayList<BarEntry>()
            NEWBARENTRY!!.add(BarEntry(0f, y0))
            NEWBARENTRY!!.add(BarEntry(1f, y1))
            NEWBARENTRY!!.add(BarEntry(2f, y2))
            NEWBARENTRY!!.add(BarEntry(3f, y3))
            NEWBARENTRY!!.add(BarEntry(4f, y4))
            NEWBARENTRY!!.add(BarEntry(5f, y5))
            NEWBARENTRY!!.add(BarEntry(6f, y6))

            Bardataset = BarDataSet(NEWBARENTRY, "Projects")

            BARDATA = BarData(Bardataset)
            barChart!!.setData(BARDATA)

            Bardataset!!.setColors(*ColorTemplate.COLORFUL_COLORS)
            barChart!!.animateY(1500)
            barChart!!.legend.isEnabled = false
        }
    }

    fun AddValuesToBARENTRY() {
        BARENTRY!!.add(BarEntry(0f, y0))
        BARENTRY!!.add(BarEntry(1f, y1))
        BARENTRY!!.add(BarEntry(2f, y2))
        BARENTRY!!.add(BarEntry(3f, y3))
        BARENTRY!!.add(BarEntry(4f, y4))
        BARENTRY!!.add(BarEntry(5f, y5))
        BARENTRY!!.add(BarEntry(6f, y6))
    }

    fun AddValuesToBarEntryLabels() {
        BarEntryLabels!!.add("Sun")
        BarEntryLabels!!.add("Mon")
        BarEntryLabels!!.add("Tues")
        BarEntryLabels!!.add("Wed")
        BarEntryLabels!!.add("Thurs")
        BarEntryLabels!!.add("Fri")
        BarEntryLabels!!.add("Sat")
    }
}