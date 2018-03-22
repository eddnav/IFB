package com.eddnav.ifb.domain.patient

import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output

/**
 * Describes a patient's information.
 *
 * @param firstName First name.
 * @param lastName Last name.
 * @param age Patient's age.
 * @param weight Patient's weight, measured in kg.
 * @param sex Patient's sex, should be 'f' or 'm'.
 * @param bloodVolume Blood volume per kg, measured in ml/kg.
 * @param fasting Hours elapsed in preoperative fasting state.
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
              age: Int, weight: Double, sex: String,
              bloodVolume: Double, fasting: Double,
              surgicalStress: Int, hemoglobin: Double,
              minHemoglobin: Double, var intake: Intake, var output: Output) {

    /**
     * @property age Patient's age.
     */
    var age: Int = 0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Age should be > 0")
            }
            field = value
        }

    /**
     * @property weight Patient's weight in kg.
     */
    var weight: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Weight should be > 0")
            }
            field = value
        }
    /**
     * @property sex Patient's sex, should be [SEX_FEMALE] or [SEX_MALE]'.
     */
    var sex: String = ""
        set(value) {
            if (value != SEX_FEMALE && value != SEX_MALE) {
                throw IllegalArgumentException("Sex should be \"$SEX_FEMALE\" or \"$SEX_MALE\"")
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
     *  @property fasting Hours elapsed in preoperative fasting state.
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
            if (value > MAX_SURGICAL_STRESS) {
                throw IllegalArgumentException("Surgical stress should be <= $MAX_SURGICAL_STRESS")
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
        this.age = age
        this.weight = weight
        this.sex = sex
        this.bloodVolume = bloodVolume
        this.fasting = fasting
        this.surgicalStress = surgicalStress
        this.hemoglobin = hemoglobin
        this.minHemoglobin = minHemoglobin
    }

    val fullName: String = "$firstName $lastName"

    companion object {
        const val SEX_MALE = "m"
        const val SEX_FEMALE = "f"
        const val MAX_SURGICAL_STRESS = 10
    }
}