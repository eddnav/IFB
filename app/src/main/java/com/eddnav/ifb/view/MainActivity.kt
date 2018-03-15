package com.eddnav.ifb.view

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.R
import com.eddnav.ifb.data.report.repository.ReportRepository
import com.eddnav.ifb.view.report.EditReportFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author Eduardo Naveda
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(R.id.mainContent, EditReportFragment.newInstance("0", "0")).commit()
        }

        val repository = ReportRepository(this.application as IFBApp)
        val reports = repository.getAllFail()
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
