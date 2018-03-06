package com.eddnav.ifb.cache.intake.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import com.eddnav.ifb.cache.intake.model.IntakeEntity

/**
 * @author Eduardo Naveda
 */
@Dao
interface IntakeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addIntake(intake: IntakeEntity): Long
}