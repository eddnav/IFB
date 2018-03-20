package com.eddnav.ifb.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.eddnav.ifb.cache.report.dao.ReportDAO
import com.eddnav.ifb.cache.report.model.ReportEntity

/**
 * @author Eduardo Naveda
 */
@Database(entities = [
    ReportEntity::class], version = 1, exportSchema = false)
@TypeConverters(IFBTypeConverters::class)
abstract class IFBDatabase : RoomDatabase() {
    abstract fun reportDAO(): ReportDAO
}