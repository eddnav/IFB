package com.eddnav.ifb.data.report.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.cache.report.converter.ReportConverter
import com.eddnav.ifb.domain.report.Report
import kotlinx.coroutines.async
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: remember to move this to Koin soon.

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
    fun get(id: Long): LiveData<Report?> {
        val report = app.database.reportDAO().get(id)
        return Transformations.map(report, {
            if (it != null) ReportConverter.toDomain(it)
            else return@map null
        })
    }

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


    /**
     * Deletes a report.
     *
     * @param id Report id to delete.
     */
    fun deleteAsync(id: Long) = async {
        val report = Report.default()
        report.id = id
        app.database.reportDAO().delete(ReportConverter.fromDomain(report))
    }
}