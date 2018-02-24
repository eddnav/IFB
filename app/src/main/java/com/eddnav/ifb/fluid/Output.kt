package com.eddnav.ifb.fluid;

/**
 * Fluid output description.
 *
 * @param diuresis Output via diuresis, measured in cc.
 * @param aspiration Output via aspiration, measured in cc.
 * @param compresses Output via compresses, measured in cc.
 * @param levinsTube Output via Levin's tube, measured in cc.
 *
 * @author Eduardo Naveda
 */
class Output(diuresis: Double, aspiration: Double, compresses: Double, levinsTube: Double) {

    /**
     * @property diuresis Output via diuresis, measured in cc.
     */
    var diuresis: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Diuresis should be >= 0")
            }
            field = value
        }

    /**
     * @property aspiration Output via aspiration, measured in cc.
     */
    var aspiration: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Aspiration should be >= 0")
            }
            field = value
        }

    /**
     * @property compresses Output via compresses, measured in cc.
     */
    var compresses: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Compresses should be >= 0")
            }
            field = value
        }

    /**
     * @property levinsTube Output via Levin's tube, measured in cc.
     */
    var levinsTube: Double = 0.0
        set(value) {
            if (value < 0) {
                throw IllegalArgumentException("Levin's tube should be >= 0")
            }
            field = value
        }
    /**
     * @property total Total fluid output, measured in cc.
     */
    var total: Double = 0.0
        get() = this.diuresis + this.aspiration + this.compresses + this.levinsTube
        private set

    init {
        this.diuresis = diuresis
        this.aspiration = aspiration
        this.compresses = compresses
        this.levinsTube = levinsTube
    }
}
