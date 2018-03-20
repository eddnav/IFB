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
import kotlinx.coroutines.experimental.async
import org.threeten.bp.OffsetDateTime
import java.util.concurrent.Callable

/**
 * @author Eduardo Naveda
 */
class ReportRepository(var app: IFBApp) { // TODO: disgusting, remember to move this to Dagger soon.

    /**
     * Returns a list of all the saved reports.
     */
    fun getAllAsync() = async {
        Transformations.map(app.database.reportDAO().getAll(), {
            it.map(FullReport::toDomain)
        })
    }

    /**
     * Returns a report for the given id.
     *
     * @param id Id of the report to get.
     */
    fun getAsync(id: Long) = async {
        Transformations.map(app.database.reportDAO().get(id), {
            it.toDomain()
        })
    }

    /**
     * Adds a new report, creates the required patient, surgery, intake and output as well.
     */
    fun addAsync(report: Report) = async {
        val database = app.database
        database.runInTransaction(Callable<Long>({
            val intakeId = database.intakeDAO().add(IntakeEntity(report.patient.intake))
            val outputId = database.outputDAO().add(OutputEntity(report.patient.output))
            val patientId = database.patientDAO().add(PatientEntity(report.patient, intakeId, outputId))
            val surgeryId = database.surgeryDAO().add(SurgeryEntity(report.surgery))

            val now = OffsetDateTime.now()
            database.reportDAO().add(ReportEntity(patientId, surgeryId, now, now))
        }))
    }
}