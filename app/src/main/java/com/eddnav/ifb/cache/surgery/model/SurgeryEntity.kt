package com.eddnav.ifb.cache.surgery.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.domain.surgery.Surgery

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Surgeries")
data class SurgeryEntity(
        var description: String,
        var duration: Double) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(surgery: Surgery) : this(surgery.description, surgery.duration)
}