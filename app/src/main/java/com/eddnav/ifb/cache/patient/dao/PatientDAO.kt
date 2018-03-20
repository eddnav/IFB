package com.eddnav.ifb.cache.patient.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.eddnav.ifb.cache.patient.model.PatientEntity

/**
 * @author Eduardo Naveda
 */
@Dao
interface PatientDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(patient: PatientEntity): Long
}