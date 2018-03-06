package com.eddnav.ifb.cache.report.representation

import android.arch.persistence.room.Ignore
import com.eddnav.ifb.cache.Representation
import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.HydrationSchedule
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.domain.surgery.Surgery

/**
 * @author Eduardo Naveda
 */
data class FullReport(
        var id: Long,
        var firstName: String,
        var lastName: String,
        var weight: Double,
        var sex: String,
        var bloodVolume: Double,
        var fasting: Double,
        var surgicalStress: Int,
        var hemoglobin: Double,
        var minHemoglobin: Double,
        var description: String,
        var duration: Double,
        var crystalloids: Double,
        var colloids: Double,
        var hemoderivatives: Double,
        var drugInfusions: Double,
        var diuresis: Double,
        var aspiration: Double,
        var compresses: Double,
        var levinsTube: Double) : Representation<Report> {

    @Ignore
    override fun toDomain(): Report {
        val intake = Intake(crystalloids, colloids, hemoderivatives, drugInfusions)
        val output = Output(diuresis, aspiration, compresses, levinsTube)
        val surgery = Surgery(description, duration)
        val patient = Patient(firstName, lastName, weight, sex, bloodVolume, fasting, surgicalStress, hemoglobin, minHemoglobin, intake, output)

        return Report(id, patient, surgery, hydrationSchedule = HydrationSchedule(patient))
    }
}