package com.eddnav.ifb.data.report.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.cache.report.converter.ReportConverter
import com.eddnav.ifb.domain.report.Report
import kotlinx.coroutines.experimental.async
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: disgusting, remember to move this to Dagger soon.

    /**
     * Returns a list of all the saved reports.
     *
     * Inverse-ordered by create date
     */
    fun getAll(): LiveData<List<Report>> = Transformations.map(app.database.reportDAO().getAll(), {
            it.map({
                ReportConverter.toDomain(it)
            })
        })


    /**
     * Returns a report for the given id.
     *
     * @param id Id of the report to get.
     */
    fun get(id: Long): LiveData<Report> = Transformations.map(app.database.reportDAO().get(id), {
                ReportConverter.toDomain(it)
            })

    /**
     * Adds a new report.
     *
     * @param report Report to add.
     */
    fun addAsync(report: Report) = async {
        val now = OffsetDateTime.now()
        report.created = now
        report.updated = now
        app.database.reportDAO().add(ReportConverter.fromDomain(report))
    }

    /**
     * Updates a report.
     *
     * @param report Report to update.
     */
    fun updateAsync(report: Report) = async {
        val now = OffsetDateTime.now()
        report.updated = now
        app.database.reportDAO().add(ReportConverter.fromDomain(report))
    }
}