package com.eddnav.ifb.report

import com.eddnav.ifb.fluid.HourlyHydration
import com.eddnav.ifb.fluid.Intake
import com.eddnav.ifb.fluid.Output
import com.eddnav.ifb.patient.Patient

/**
 * @author Eduardo Naveda
 */
class Report(var patient: Patient, var intake: Intake, var output: Output,
             var hourlyHydration: HourlyHydration, surgeryDuration: Double) {

    var surgeryDuration: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Surgery duration should be > 0")
            }
            field = value
        }

    var minimumAllowableBloodLoss: Double = 0.0
        get() = ((this.patient.hemoglobin - this.patient.minHemoglobin) / this.patient.hemoglobin) * this.patient.bloodVolume * this.patient.weight
        private set

    var hourlyDiuresis: Double = 0.0
        get() = this.output.diuresis / this.surgeryDuration
        private set

    var urineOutput: Double = 0.0
        get() = this.hourlyDiuresis / this.patient.weight
        private set

    var intakeSupply: Double = 0.0
        get() = this.intake.total / this.patient.weight
        private set

    var finalFluidBalance: Double = 0.0
        get() = this.intake.total - this.output.total
        private set

    init {
        this.surgeryDuration = surgeryDuration
    }
}