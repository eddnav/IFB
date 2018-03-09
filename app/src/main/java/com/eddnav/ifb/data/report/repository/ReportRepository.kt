package com.eddnav.ifb.data.report.repository

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
    fun getAll() = Transformations.map(app.database.reportDAO().getAll(), {
        it.map(FullReport::toDomain)
    })

    fun getAllFail() = app.database.reportDAO().getAllFail() // TODO: Remove

    /**
     * Returns a report for the given id.
     *
     * @param id Id of the report to get.
     */
    fun get(id: Long) = Transformations.map(app.database.reportDAO().get(id), {
        it.toDomain()
    })

    /**
     * Adds a new report, creates the required patient, surgery, intake and output as well.
     */
    fun add(report: Report) {
        val database = app.database
        database.runInTransaction {
            val intakeId = database.intakeDAO().addIntake(IntakeEntity(report.patient.intake))
            val outputId = database.outputDAO().addOutput(OutputEntity(report.patient.output))
            val patientId = database.patientDAO().addPatient(PatientEntity(report.patient, intakeId, outputId))
            val surgeryId = database.surgeryDAO().addSurgery(SurgeryEntity(report.surgery))

            database.reportDAO().add(ReportEntity(patientId, surgeryId))
        }
    }
}