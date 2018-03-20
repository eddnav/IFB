package com.eddnav.ifb.cache.output.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.eddnav.ifb.cache.output.model.OutputEntity

/**
 * @author Eduardo Naveda
 */
@Dao
interface OutputDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(output: OutputEntity): Long
}