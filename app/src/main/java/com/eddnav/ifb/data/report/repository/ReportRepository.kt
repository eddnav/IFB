package com.eddnav.ifb.data.report.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.cache.intake.model.IntakeEntity
import com.eddnav.ifb.cache.output.model.OutputEntity
import com.eddnav.ifb.cache.patient.model.PatientEntity
import com.eddnav.ifb.cache.report.model.ReportEntity
import com.eddnav.ifb.cache.report.representation.FullReport
import com.eddnav.ifb.cache.surgery.model.SurgeryEntity
import com.eddnav.ifb.domain.report.Report

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: disgusting, remember to move this to Dagger soon.

    /**
     * Returns a list of all the saved reports.
     */
    fun getReports() : LiveData<List<Report>> = Transformations.map(app.database.reportDAO().getReports(), {
        it.map(FullReport::toDomain)
    })

    /**
     * Returns a report for the given id.
     *
     * @param id Id of the report to get.
     */
    fun getReport(id: Long) : LiveData<Report> = Transformations.map(app.database.reportDAO().getReport(id), {
        it.toDomain()
    })

    /**
     * Adds a new report, creates the required patient, surgery, intake and output as well.
     */
    fun addReport(report: Report) {
        val database = app.database
            database.runInTransaction {
            val intakeId = database.intakeDAO().addIntake(IntakeEntity(report.patient.intake))
            val outputId = database.outputDAO().addOutput(OutputEntity(report.patient.output))
            val patientId = database.patientDAO().addPatient(PatientEntity(report.patient, intakeId, outputId))
            val surgeryId = database.surgeryDAO().addSurgery(SurgeryEntity(report.surgery))

            database.reportDAO().addReport(ReportEntity(patientId, surgeryId))
        }
    }
}