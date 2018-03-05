package com.eddnav.ifb.patient.persistence

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.fluid.persistence.IntakeRow
import com.eddnav.ifb.fluid.persistence.OutputRow
import com.eddnav.ifb.patient.Patient

/**
 * @author Eduardo Naveda
 */
@Entity(
        tableName = "Patients",
        foreignKeys = [
            (ForeignKey(entity = IntakeRow::class,
                    parentColumns = ["id"],
                    childColumns = ["intakeId"],
                    onDelete = CASCADE)),
            (ForeignKey(entity = OutputRow::class,
                    parentColumns = ["id"],
                    childColumns = ["outputId"],
                    onDelete = CASCADE))
        ],
        indices = [
            (Index("intakeId")),
            (Index("outputId"))
        ]
)
data class PatientRow(
        var firstName: String,
        var lastName: String,
        var weight: Double,
        var sex: String,
        var bloodVolume: Double,
        var fasting: Double,
        var surgicalStress: Int,
        var hemoglobin: Double,
        var minHemoglobin: Double,
        var intakeId: Long,
        var outputId: Long) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(patient: Patient, intakeId: Long, outputId: Long) : this(patient.firstName, patient.lastName, patient.weight,
            patient.sex, patient.bloodVolume, patient.fasting, patient.surgicalStress, patient.hemoglobin, patient.minHemoglobin, intakeId, outputId)

}