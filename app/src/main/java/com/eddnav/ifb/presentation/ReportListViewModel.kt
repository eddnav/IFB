package com.eddnav.ifb.presentation

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.data.report.repository.ReportRepository
import com.eddnav.ifb.domain.report.Report

/**
 * @author Eduardo Naveda
 */
class ReportListViewModel(application: Application) : AndroidViewModel(application) { // TODO: fix all of this app garbage with dagger2 + factories

    var repository: ReportRepository = ReportRepository(application as IFBApp)

    var reports: LiveData<List<Report>> = repository.getAll()
}