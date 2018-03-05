package com.eddnav.ifb.surgery

/**
 * @author Eduardo Naveda
 */
class Surgery(var description: String, duration: Double) {

    var duration: Double = 0.0
        set(value) {
            if (value <= 0) {
                throw IllegalArgumentException("Surgery duration should be > 0")
            }
            field = value
        }

    init {
        this.duration = duration
    }
}