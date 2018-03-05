package com.eddnav.ifb.patient.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

/**
 * @author Eduardo Naveda
 */
@Dao
interface PatientDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPatient(patient: PatientRow): Long
}