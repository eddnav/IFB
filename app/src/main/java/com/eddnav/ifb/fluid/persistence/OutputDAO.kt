package com.eddnav.ifb.fluid.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy

/**
 * @author Eduardo Naveda
 */
@Dao
interface OutputDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOutput(output: OutputRow): Long
}