package com.eddnav.ifb.cache.output.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.domain.output.Output

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Outputs")
data class OutputEntity(
        var diuresis: Double,
        var aspiration: Double,
        var compresses: Double,
        var levinsTube: Double) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(output: Output) : this(output.diuresis, output.aspiration, output.compresses, output.levinsTube)
}