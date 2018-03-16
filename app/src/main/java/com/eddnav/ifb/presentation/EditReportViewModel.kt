package com.eddnav.ifb.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.data.report.repository.ReportRepository
import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.HydrationSchedule
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.domain.surgery.Surgery

/**
 * @author Eduardo Naveda
 */
class EditReportViewModel(application: Application) : AndroidViewModel(application) { // TODO: fix all of this app garbage with dagger2 + factories

    var repository: ReportRepository = ReportRepository(application as IFBApp)

    var report: MutableLiveData<Report> = MutableLiveData()
        private set

    fun load(id: Long? = null) {
        if (id !== null) {
            Transformations.map(repository.get(id), {
                report.postValue(it)
            })
        } else {
            val patient = Patient("", "", 10, 60.0, // TODO: add a create default report
                    "f", 70.0, 0.0, 0, 13.5,
                    13.5,
                    Intake(0.0, 0.0, 0.0, 0.0),
                    Output(0.0, 0.0, 0.0, 0.0))
            report.postValue(Report(patient,
                    Surgery("", 1.0),
                    HydrationSchedule(patient)))
        }
    }


    fun save(report: Report) {
        repository.add(report)
    }
}