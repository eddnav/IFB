package com.eddnav.ifb.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.data.report.repository.ReportRepository
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.vendor.SingleLiveEvent
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

/**
 * @author Eduardo Naveda
 */
class EditReportViewModel(application: Application) : AndroidViewModel(application) { // TODO: fix all of this app garbage with dagger2 + factories

    var repository: ReportRepository = ReportRepository(application as IFBApp)

    var report: MutableLiveData<Report> = MutableLiveData()
        private set

    var saveSuccessEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
        private set

    fun load(id: Long? = null) {
        if (id !== null) {
            launch (UI) {
                Transformations.map(repository.getAsync(id).await(), {
                    report.value = it
                })
            }
        } else {
            report.value = Report.default()
        }
    }


    fun save(report: Report) {
        launch(UI) {
            repository.addAsync(report).await()
            saveSuccessEvent.call()
        }
    }
}