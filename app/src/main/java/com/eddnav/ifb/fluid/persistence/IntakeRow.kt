package com.eddnav.ifb.fluid.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.fluid.Intake

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Intakes")
data class IntakeRow(
        var crystalloids: Double,
        var colloids: Double,
        var hemoderivatives: Double,
        var drugInfusions: Double) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(intake: Intake) : this(intake.crystalloids, intake.colloids, intake.hemoderivatives, intake.drugInfusions)
}