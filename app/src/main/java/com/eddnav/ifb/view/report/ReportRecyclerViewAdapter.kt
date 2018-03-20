package com.eddnav.ifb.view.report

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eddnav.ifb.R
import com.eddnav.ifb.domain.report.Report
import kotlinx.android.synthetic.main.fragment_report_list_item.view.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

class ReportRecyclerViewAdapter(private val values: List<Report>, private val listener: (Report) -> Unit) : RecyclerView.Adapter<ReportRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_report_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(values[position])
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val fullName: TextView = view.fullName
        val created: TextView = view.created

        fun bind(report: Report) {
            fullName.text = report.patient.fullName
            created.text = report.created?.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) ?: ""
            view.setOnClickListener { listener(report) }
        }
    }
}
