package com.eddnav.ifb.cache.report.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.cache.patient.model.PatientEntity
import com.eddnav.ifb.cache.surgery.model.SurgeryEntity
import org.threeten.bp.OffsetDateTime

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Reports",
        foreignKeys = [
            ForeignKey(entity = PatientEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["patientId"],
                    onDelete = CASCADE),
            ForeignKey(entity = SurgeryEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["surgeryId"],
                    onDelete = CASCADE)
        ],
        indices = [
            Index("patientId"),
            Index("surgeryId")
        ]
)
data class ReportEntity(
        var patientId: Long, var surgeryId: Long, var created: OffsetDateTime, var updated: OffsetDateTime) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}