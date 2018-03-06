package com.eddnav.ifb.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eddnav.ifb.cache.intake.dao.IntakeDAO
import com.eddnav.ifb.cache.intake.model.IntakeEntity
import com.eddnav.ifb.cache.output.dao.OutputDAO
import com.eddnav.ifb.cache.output.model.OutputEntity
import com.eddnav.ifb.cache.patient.dao.PatientDAO
import com.eddnav.ifb.cache.patient.model.PatientEntity
import com.eddnav.ifb.cache.report.dao.ReportDAO
import com.eddnav.ifb.cache.report.model.ReportEntity
import com.eddnav.ifb.cache.surgery.dao.SurgeryDAO
import com.eddnav.ifb.cache.surgery.model.SurgeryEntity

/**
 * @author Eduardo Naveda
 */
@Database(entities = [
    PatientEntity::class,
    SurgeryEntity::class,
    IntakeEntity::class,
    OutputEntity::class,
    ReportEntity::class], version = 1, exportSchema = false)
abstract class IFBDatabase : RoomDatabase() {

    abstract fun patientDAO(): PatientDAO
    abstract fun surgeryDAO(): SurgeryDAO
    abstract fun intakeDAO(): IntakeDAO
    abstract fun outputDAO(): OutputDAO
    abstract fun reportDAO(): ReportDAO
}