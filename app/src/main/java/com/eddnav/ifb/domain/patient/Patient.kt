package com.eddnav.ifb.domain.patient

import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output

/**
 * Describes a patient's information.
 *
 * @param firstName First name.
 * @param lastName Last name.
 * @param weight Weight of the patient, measured in kg.
 * @param sex Sex of the patient, should be 'f' or 'm'.
 * @param bloodVolume Blood volume per kg, measured in ml/kg.
 * @param fasting Hours elapsed in pathological fasting state.
 * @param surgicalStress Measured surgical stress.
 * @param hemoglobin Hemoglobin, measured in g/dl.
 * @param minHemoglobin Minimum final allowable hemoglobin, measured in g/dl.
 *
 * @author Eduardo Naveda
 *
 * @property firstName First name.
 * @property lastName Last name.
 */
class Patient(var firstName: String, var lastName: String,
              weight: Double, sex: String, bloodVolume: Double,
              fasting: Double, surgicalStress: Int,
              hemoglobin: Double, minHemoglobin: Double,
              var intake: Intake, var output: Output) {

    /**
     * @property weight Weight of the patient in kg.
     */
    var weight: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Weight should be > 0")
            }
            field = value
        }
    /**
     * @property sex Sex of the patient, should be 'f' or 'm'.
     */
    var sex: String = ""
        set(value) {
            if (value != "m" && value != "f") {
                throw IllegalArgumentException("Sex should be \"f\" or \"m\"")
            }
            field = value
        }

    /**
     * @property bloodVolume Blood volume per kg, measured in ml/kg.
     */
    var bloodVolume: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Blood volume should be > 0")
            }
            field = value
        }

    /**
     *  @property fasting Hours elapsed in pathological fasting state.
     */
    var fasting: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Fasting hours should be >= 0")
            }
            field = value
        }

    /**
     * @property surgicalStress Measured surgical stress.
     */
    var surgicalStress: Int = 0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Surgical stress should be >= 0")
            }
            field = value
        }

    /**
     * @property hemoglobin Hemoglobin, measured in g/dl.
     */
    var hemoglobin: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Hemoglobin should be > 0")
            }
            field = value
        }

    /**
     * @property minHemoglobin Minimum final allowable hemoglobin, measured in g/dl.
     */
    var minHemoglobin: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Min hemoglobin should be > 0")
            }
            field = value
        }

    init {
        this.weight = weight
        this.sex = sex
        this.bloodVolume = bloodVolume
        this.fasting = fasting
        this.surgicalStress = surgicalStress
        this.hemoglobin = hemoglobin
        this.minHemoglobin = minHemoglobin
    }
}