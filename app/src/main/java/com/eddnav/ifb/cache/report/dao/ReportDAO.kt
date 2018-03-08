package com.eddnav.ifb.cache.report.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.eddnav.ifb.cache.report.Constants
import com.eddnav.ifb.cache.report.model.ReportEntity
import com.eddnav.ifb.cache.report.representation.FullReport

/**
 * @author Eduardo Naveda
 */
@Dao
interface ReportDAO {

    @Query(Constants.QUERY_GET_REPORT)
    fun getReport(id: Long): LiveData<FullReport>

    @Query(Constants.QUERY_GET_REPORTS)
    fun getReports(): LiveData<List<FullReport>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReport(report: ReportEntity): Long
}