package com.eddnav.ifb.cache.report.representation

import android.arch.persistence.room.Ignore
import com.eddnav.ifb.cache.Representation
import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.HydrationSchedule
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.domain.surgery.Surgery
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
data class FullReport(
        val id: Long,
        val firstName: String,
        val lastName: String,
        val weight: Double,
        val sex: String,
        val bloodVolume: Double,
        val fasting: Double,
        val surgicalStress: Int,
        val hemoglobin: Double,
        val minHemoglobin: Double,
        val description: String,
        val duration: Double,
        val crystalloids: Double,
        val colloids: Double,
        val hemoderivatives: Double,
        val drugInfusions: Double,
        val diuresis: Double,
        val aspiration: Double,
        val compresses: Double,
        val levinsTube: Double,
        val created: OffsetDateTime?,
        val updated: OffsetDateTime?) : Representation<Report> {

    @Ignore
    override fun toDomain(): Report {
        val intake = Intake(crystalloids, colloids, hemoderivatives, drugInfusions)
        val output = Output(diuresis, aspiration, compresses, levinsTube)
        val surgery = Surgery(description, duration)
        val patient = Patient(firstName, lastName, 10, weight, sex, bloodVolume, fasting, surgicalStress, hemoglobin, minHemoglobin, intake, output)

        return Report(id, patient, surgery, HydrationSchedule(patient), created, updated)
    }
}