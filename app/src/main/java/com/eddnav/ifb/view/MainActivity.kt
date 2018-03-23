package com.eddnav.ifb.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eddnav.ifb.R
import com.eddnav.ifb.view.report.EditReportActivity
import com.eddnav.ifb.view.report.ReportListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author Eduardo Naveda
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = getString(R.string.title_reports)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().add(
                    R.id.content, ReportListFragment.newInstance()
            ).commit()
        }

        fab.setOnClickListener {
            startActivity(Intent(this, EditReportActivity::class.java))
        }
    }
}
