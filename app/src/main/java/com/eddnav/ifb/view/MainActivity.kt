package com.eddnav.ifb.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eddnav.ifb.R
import com.eddnav.ifb.view.report.EditReportActivity
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

        fab.setOnClickListener {
            startActivity(Intent(this, EditReportActivity::class.java))
        }

        //val repository = ReportRepository(this.application as IFBApp)
        //val reports = repository.getAllFail()
        print("hello world")
    }
}
