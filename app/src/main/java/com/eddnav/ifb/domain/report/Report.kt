package com.eddnav.ifb.domain.report

import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.surgery.Surgery

/**
 * @author Eduardo Naveda
 */
class Report(var id: Long, var patient: Patient, var surgery: Surgery, var hydrationSchedule: HydrationSchedule) {

    var minimumAllowableBloodLoss: Double = 0.0
        get() = ((this.patient.hemoglobin - this.patient.minHemoglobin) / this.patient.hemoglobin) * this.patient.bloodVolume * this.patient.weight
        private set

    var hourlyDiuresis: Double = 0.0
        get() = this.patient.output.diuresis / this.surgery.duration
        private set

    var urineOutput: Double = 0.0
        get() = this.hourlyDiuresis / this.patient.weight
        private set

    var intakeSupply: Double = 0.0
        get() = this.patient.intake.total / this.patient.weight
        private set

    var finalFluidBalance: Double = 0.0
        get() = this.patient.intake.total - this.patient.output.total
        private set

    constructor(patient: Patient, surgery: Surgery, hydrationSchedule: HydrationSchedule) : this(0, patient, surgery, hydrationSchedule)
}