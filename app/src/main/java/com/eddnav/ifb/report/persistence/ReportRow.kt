package com.eddnav.ifb.report.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.patient.persistence.PatientRow
import com.eddnav.ifb.surgery.persistence.SurgeryRow

/**
 * @author Eduardo Naveda
 */
@Entity(tableName = "Reports",
        foreignKeys = [
            ForeignKey(entity = PatientRow::class,
                    parentColumns = ["id"],
                    childColumns = ["patientId"],
                    onDelete = CASCADE),
            ForeignKey(entity = SurgeryRow::class,
                    parentColumns = ["id"],
                    childColumns = ["surgeryId"],
                    onDelete = CASCADE)
        ],
        indices = [
            Index("patientId"),
            Index("surgeryId")
        ]
)
data class ReportRow(
        var patientId: Long, var surgeryId: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}