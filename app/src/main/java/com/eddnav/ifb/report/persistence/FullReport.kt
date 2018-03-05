package com.eddnav.ifb.report.persistence

import android.arch.persistence.room.Ignore
import com.eddnav.ifb.fluid.HydrationSchedule
import com.eddnav.ifb.fluid.Intake
import com.eddnav.ifb.fluid.Output
import com.eddnav.ifb.patient.Patient
import com.eddnav.ifb.persistence.Representation
import com.eddnav.ifb.report.Report
import com.eddnav.ifb.surgery.Surgery

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