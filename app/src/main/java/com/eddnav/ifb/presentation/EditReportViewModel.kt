package com.eddnav.ifb.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.data.report.repository.ReportRepository
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.vendor.SingleLiveEvent
import kotlinx.coroutines.android.UI
import kotlinx.coroutines.launch

/**
 * @author Eduardo Naveda
 */
class EditReportViewModel(application: Application) : AndroidViewModel(application) { // TODO: fix all of this app garbage with dagger2 + factories

    var repository: ReportRepository = ReportRepository(application as IFBApp)

    private var report: LiveData<Report?>? = null

    var saveSuccessEvent: SingleLiveEvent<SaveSuccessEvent> = SingleLiveEvent()
        private set

    fun get(id: Long): LiveData<Report?> {
        report = repository.get(id)
        return report!!
    }

    fun save(report: Report) {
        // TODO: Fix this deprecation.
        launch(UI) {
            val isNew = report.id == null
            var id = report.id
            if (isNew) {
                id = repository.addAsync(report).await()
            } else {
                repository.updateAsync(report).await()
            }
            saveSuccessEvent.postValue(SaveSuccessEvent(id!!, isNew))
        }
    }
}