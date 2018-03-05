package com.eddnav.ifb.report.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * @author Eduardo Naveda
 */
@Dao
interface ReportDAO {

    @Query("SELECT Reports.id AS id, firstName, lastName, weight, sex, bloodVolume, " +
            "fasting, surgicalStress, hemoglobin, minHemoglobin, crystalloids, " +
            "colloids, hemoderivatives, drugInfusions, diuresis, aspiration, " +
            "compresses, levinsTube, description, duration FROM Reports INNER JOIN Surgeries " +
            "on Reports.surgeryId = Surgeries.id INNER JOIN (SELECT Patients.id, firstName, " +
            "lastName, weight, sex, bloodVolume, fasting, surgicalStress, hemoglobin, " +
            "minHemoglobin, crystalloids, colloids, hemoderivatives, drugInfusions, diuresis, " +
            "aspiration, compresses, levinsTube from Patients INNER JOIN Intakes on " +
            "Patients.intakeId = Intakes.id INNER JOIN Outputs on Patients.outputId = Outputs.id) " +
            "P on P.id = Reports.patientId")
    fun getReports(): List<FullReport>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReport(report: ReportRow): Long
}