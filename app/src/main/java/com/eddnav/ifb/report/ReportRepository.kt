package com.eddnav.ifb.report

import com.eddnav.ifb.IFBApp
import com.eddnav.ifb.fluid.persistence.IntakeRow
import com.eddnav.ifb.fluid.persistence.OutputRow
import com.eddnav.ifb.patient.persistence.PatientRow
import com.eddnav.ifb.report.persistence.ReportRow
import com.eddnav.ifb.surgery.persistence.SurgeryRow

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: disgusting, remember to move this to Dagger soon.

    fun getAllReports() {
        val reports = app.database.reportDAO().getReports().map {
            it.toDomain()
        }
    }

    fun addReport(report: Report) {
        val database = app.database
        database.runInTransaction {
            val patient = report.patient
            val surgery = report.surgery
            val intake = report.patient.intake
            val output = report.patient.output
            val intakeId = database.intakeDAO().addIntake(IntakeRow(intake))
            val outputId = database.outputDAO().addOutput(OutputRow(output))
            val patientId = database.patientDAO().addPatient(PatientRow(patient, intakeId, outputId))
            val surgeryId = database.surgeryDAO().addSurgery(SurgeryRow(surgery))
            database.reportDAO().addReport(ReportRow(patientId, surgeryId))
        }
    }

}