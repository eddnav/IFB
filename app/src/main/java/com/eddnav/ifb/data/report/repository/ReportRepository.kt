package com.eddnav.ifb.data.report.repository

import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.cache.intake.model.IntakeEntity
import com.eddnav.ifb.cache.output.model.OutputEntity
import com.eddnav.ifb.cache.patient.model.PatientEntity
import com.eddnav.ifb.cache.report.model.ReportEntity
import com.eddnav.ifb.cache.surgery.model.SurgeryEntity
import com.eddnav.ifb.domain.report.Report

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: disgusting, remember to move this to Dagger soon.

    fun getAllReports() {
        val reports = app.database.reportDAO().getReports().map {
            it.toDomain()
        }
    }

    /**
     * Adds a new report, creates the required patient, surgery, intake and output as well.
     */
    fun addReport(report: Report) {
        val database = app.database
        database.runInTransaction {
            val patient = report.patient
            val surgery = report.surgery
            val intake = report.patient.intake
            val output = report.patient.output
            val intakeId = database.intakeDAO().addIntake(IntakeEntity(intake))
            val outputId = database.outputDAO().addOutput(OutputEntity(output))
            val patientId = database.patientDAO().addPatient(PatientEntity(patient, intakeId, outputId))
            val surgeryId = database.surgeryDAO().addSurgery(SurgeryEntity(surgery))
            database.reportDAO().addReport(ReportEntity(patientId, surgeryId))
        }
    }
}