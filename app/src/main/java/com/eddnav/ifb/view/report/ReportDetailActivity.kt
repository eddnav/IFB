package com.eddnav.ifb.view.report

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eddnav.ifb.R
import kotlinx.android.synthetic.main.toolbar.*

class ReportDetailActivity : AppCompatActivity(), ReportDetailFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {

            if (intent.extras != null) {
                supportFragmentManager.beginTransaction().add(
                        R.id.content, ReportDetailFragment.newInstance(intent.getLongExtra(ReportDetailActivity.ARG_ID, -1)
                )).commit()
            } else throw IllegalArgumentException("getExtras() is null, please add ${ReportDetailActivity.ARG_ID} to the intent.")
        }
    }

    override fun onDeleteSuccess() {
        finish()
    }

    companion object {
        const val ARG_ID = "arg_id"
    }
}
