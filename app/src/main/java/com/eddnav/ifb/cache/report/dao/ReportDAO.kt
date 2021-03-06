package com.eddnav.ifb.cache.report.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.eddnav.ifb.cache.report.Constants
import com.eddnav.ifb.cache.report.model.ReportEntity

/**
 * @author Eduardo Naveda
 */
@Dao
interface ReportDAO {

    @Query(Constants.QUERY_GET_REPORT)
    fun get(id: Long): LiveData<ReportEntity>

    @Query(Constants.QUERY_GET_REPORTS)
    fun getAll(): LiveData<List<ReportEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(report: ReportEntity): Long

    @Delete
    fun delete(report: ReportEntity)
}