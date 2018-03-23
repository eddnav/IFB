package com.eddnav.ifb.domain.report

import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.surgery.Surgery
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
class Report(var id: Long?, var patient: Patient, var surgery: Surgery, var hydrationSchedule: HydrationSchedule, var created: OffsetDateTime?, var updated: OffsetDateTime?) {

    /**
     * In cc.
     */
    var minimumAllowableBloodLoss: Double = 0.0
        get() = ((this.patient.hemoglobin - this.patient.minHemoglobin) / this.patient.hemoglobin) * this.patient.bloodVolume * this.patient.weight
        private set

    /**
     * In cc/hr.
     */
    var hourlyDiuresis: Double = 0.0
        get() = this.patient.output.diuresis / this.surgery.duration
        private set

    /**
     * In cc/kg/hr.
     */
    var urineOutput: Double = 0.0
        get() = this.hourlyDiuresis / this.patient.weight
        private set

    /**
     * In cc/kg.
     */
    var intakeSupply: Double = 0.0
        get() = this.patient.intake.total / this.patient.weight
        private set

    /**
     * In cc.
     */
    var finalFluidBalance: Double = 0.0
        get() = this.patient.intake.total - this.patient.output.total
        private set

    constructor(patient: Patient, surgery: Surgery, hydrationSchedule: HydrationSchedule) : this(null, patient, surgery, hydrationSchedule, null, null)

    companion object {

        fun default(): Report {
            val patient = Patient("", "", 20, 60.0,
                    Patient.SEX_FEMALE, Patient.DEFAULT_FEMALE_BLOOD_VOLUME, 0.0, 0, Patient.DEFAULT_HEMOGLOBIN,
                    Patient.DEFAULT_HEMOGLOBIN,
                    Intake(0.0, 0.0, 0.0, 0.0),
                    Output(0.0, 0.0, 0.0, 0.0))
            return Report(patient,
                    Surgery("", 1.0),
                    HydrationSchedule(patient))
        }
    }
}