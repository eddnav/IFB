package com.eddnav.ifb.view.report

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eddnav.ifb.R
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.presentation.ReportListViewModel

/**
 * @author Eduardo Naveda
 */
class ReportListFragment : Fragment() {

    private lateinit var mViewModel: ReportListViewModel
    private lateinit var mAdapter: ReportRecyclerViewAdapter

    private var mReports: MutableList<Report> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(ReportListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_report_list, container, false) as RecyclerView
        view.layoutManager = LinearLayoutManager(context)
        mAdapter = ReportRecyclerViewAdapter(mReports, {
            val intent = Intent(context, ReportDetailActivity::class.java)
            intent.putExtra(ReportDetailActivity.ARG_ID, it.id)
            startActivity(intent)
        })
        view.adapter = mAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel.reports.observe(this, Observer<List<Report>> {
            mReports.clear()
            mReports.addAll(it!!)
            mAdapter.notifyDataSetChanged()
        })
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment.
         */
        fun newInstance(): ReportListFragment {
            return ReportListFragment()
        }
    }
}
