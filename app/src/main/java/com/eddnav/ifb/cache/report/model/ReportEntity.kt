package com.eddnav.ifb.cache.report.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Reports"
)
data class ReportEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Long?,
        val firstName: String,
        val lastName: String,
        val age: Int,
        val weight: Double,
        val sex: String,
        val bloodVolume: Double,
        val fasting: Double,
        val surgicalStress: Int,
        val hemoglobin: Double,
        val minHemoglobin: Double,
        val description: String,
        val duration: Double,
        val crystalloids: Double,
        val colloids: Double,
        val hemoderivatives: Double,
        val drugInfusions: Double,
        val diuresis: Double,
        val aspiration: Double,
        val compresses: Double,
        val levinsTube: Double,
        val created: OffsetDateTime?,
        val updated: OffsetDateTime?)
