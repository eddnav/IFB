package com.eddnav.ifb.cache.patient.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.eddnav.ifb.cache.intake.model.IntakeEntity
import com.eddnav.ifb.cache.output.model.OutputEntity
import com.eddnav.ifb.domain.patient.Patient

/**
 * Not meant to kept in catalog, this entity is simply an in-time representation of a patient's medical
 * information.
 *
 * @author Eduardo Naveda
 */
@Entity(
        tableName = "Patients",
        foreignKeys = [
            (ForeignKey(entity = IntakeEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["intakeId"],
                    onDelete = CASCADE)),
            (ForeignKey(entity = OutputEntity::class,
                    parentColumns = ["id"],
                    childColumns = ["outputId"],
                    onDelete = CASCADE))
        ],
        indices = [
            (Index("intakeId")),
            (Index("outputId"))
        ]
)
data class PatientEntity(
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