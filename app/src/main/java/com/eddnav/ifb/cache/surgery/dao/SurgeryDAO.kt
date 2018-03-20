package com.eddnav.ifb.cache.surgery.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.eddnav.ifb.cache.surgery.model.SurgeryEntity

/**
 * @author Eduardo Naveda
 */
@Dao
interface SurgeryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(surgery: SurgeryEntity): Long
}