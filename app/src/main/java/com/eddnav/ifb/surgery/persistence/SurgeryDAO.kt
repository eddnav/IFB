package com.eddnav.ifb.surgery.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

/**
 * @author Eduardo Naveda
 */
@Dao
interface SurgeryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addSurgery(surgery: SurgeryRow): Long
}