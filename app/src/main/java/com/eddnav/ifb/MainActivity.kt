package com.eddnav.ifb

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.eddnav.ifb.fluid.HourlyHydration
import com.eddnav.ifb.fluid.Intake
import com.eddnav.ifb.fluid.Output
import com.eddnav.ifb.patient.Patient
import com.eddnav.ifb.report.Report

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val patient = Patient("Pat", "Noobie", 44.3, "m",
                bloodVolume = 60.0, fasting = 2.0, surgicalStress = 2, hemoglobin = 12.0,
                minHemoglobin = 5.0)
        val hourlyHydration = HourlyHydration(patient)
        val intake = Intake(2.3, 4.2, 3.2, 3.4)
        val output = Output(4.3, 1.2, 2.2, 1.5)
        val report = Report(patient, intake, output, hourlyHydration, 3.0)
        print(report)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =// Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            when (item.itemId) {
                R.id.action_settings -> true
                else -> super.onOptionsItemSelected(item)
            }
}
