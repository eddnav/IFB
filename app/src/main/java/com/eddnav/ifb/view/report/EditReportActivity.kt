package com.eddnav.ifb.view.report

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.eddnav.ifb.R
import kotlinx.android.synthetic.main.toolbar.*

/**
 * @author Eduardo Naveda
 */
class EditReportActivity : AppCompatActivity(), EditReportFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_report)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            if (intent.extras != null) {
                title = getString(R.string.title_edit_report)
                supportFragmentManager.beginTransaction().add(
                        R.id.content, EditReportFragment.newInstance(intent.getLongExtra(ARG_ID, -1)
                )).commit()
            } else {
                title = getString(R.string.title_new_report)
                supportFragmentManager.beginTransaction().add(R.id.content, EditReportFragment.newInstance()).commit()
            }
        }
    }

    override fun onSaveSuccess() {
        finish()
    }

    companion object {
        private const val ARG_ID = "id"
    }
}
