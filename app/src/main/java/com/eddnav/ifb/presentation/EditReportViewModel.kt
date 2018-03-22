package com.eddnav.ifb.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
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

    private var report: LiveData<Report>? = null

    var saveSuccessEvent: SingleLiveEvent<SaveSuccessEvent> = SingleLiveEvent()
        private set

    fun get(id: Long): LiveData<Report> {
        if (report != null) report = repository.get(id)
        return report!!
    }

    fun save(report: Report, isNew: Boolean) {
        launch(UI) {
            val id = repository.addAsync(report).await()
            saveSuccessEvent.postValue(SaveSuccessEvent(id, isNew))
        }
    }
}