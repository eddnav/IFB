package com.eddnav.ifb.domain.intake

/**
 * Fluid output description.
 *
 * @param crystalloids Intake of crystalloids, measured in cc.
 * @param colloids Intake of colloids, measured in cc.
 * @param hemoderivatives Intake of hemoderivatives, measured in cc.
 * @param drugInfusions Intake of drug infusions, measured in cc.
 *
 * @author Eduardo Naveda
 */
class Intake(crystalloids: Double, colloids: Double, hemoderivatives: Double, drugInfusions: Double) {

    /**
     * @property crystalloids Intake of crystalloids, measured in cc.
     */
    var crystalloids: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Crystalloids should be >= 0")
            }
            field = value
        }

    /**
     * @property colloids Intake of colloids, measured in cc.
     */
    var colloids: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Colloids should be >= 0")
            }
            field = value
        }

    /**
     * @property hemoderivatives Intake of hemoderivatives, measured in cc.
     */
    var hemoderivatives: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Hemoderivatives should be >= 0")
            }
            field = value
        }

    /**
     * @property drugInfusions Intake of drug infusions, measured in cc.
     */
    var drugInfusions: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Drug infusions should be >= 0")
            }
            field = value
        }

    /**
     * @property total Total fluid intake, measured in cc.
     */
    var total: Double = 0.0
        get() = this.crystalloids + this.colloids + this.hemoderivatives + this.drugInfusions
        private set

    init {
        this.crystalloids = crystalloids
        this.colloids = colloids
        this.hemoderivatives = hemoderivatives
        this.drugInfusions = drugInfusions
    }
}