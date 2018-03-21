package com.eddnav.ifb.domain.report

import com.eddnav.ifb.domain.patient.Patient

/**
 * Hourly hydration schedule.
 *
 * @property patient Patient's info.
 *
 * @author Eduardo Naveda
 */
class HydrationSchedule(private var patient: Patient) {

    /**
     * @property base Array describing four hours of base hydration.
     */
    var base: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        get() = DoubleArray(4, { patient.weight * 2 })
        private set

    /**
     * @property insensibleLosses Array describing four hours of hydration due to insensible losses.
     */
    var insensibleLosses: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        get() = DoubleArray(4, { patient.weight * 2 })
        private set

    /**
     * @property fasting Array describing four hours of hydration due to preoperative fasting.
     */
    var fasting: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        get() = doubleArrayOf(patient.weight * patient.fasting,
                patient.weight * patient.fasting / 2,
                patient.weight * patient.fasting / 2,
                0.0)
        private set

    /**
     * @property surgicalStress Array describing four hours of hydration due to surgical stress.
     */
    var surgicalStress: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        get() = DoubleArray(4, { patient.weight * patient.surgicalStress })
        private set

    /**
     * @property total Array describing scheduled four hours hydration, total per hour.
     */
    var total: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)
        get() = DoubleArray(4, { i -> this.base[i] + this.insensibleLosses[i] + this.fasting[i] + this.surgicalStress[i] })
        private set
}