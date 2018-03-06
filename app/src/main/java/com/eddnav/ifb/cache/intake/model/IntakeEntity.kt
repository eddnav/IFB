package com.eddnav.ifb.cache.intake.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.domain.intake.Intake

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Intakes")
data class IntakeEntity(
        var crystalloids: Double,
        var colloids: Double,
        var hemoderivatives: Double,
        var drugInfusions: Double) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(intake: Intake) : this(intake.crystalloids, intake.colloids, intake.hemoderivatives, intake.drugInfusions)
}