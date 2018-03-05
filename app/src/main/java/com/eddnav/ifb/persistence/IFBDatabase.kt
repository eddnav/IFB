package com.eddnav.ifb.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.eddnav.ifb.fluid.persistence.IntakeDAO
import com.eddnav.ifb.fluid.persistence.IntakeRow
import com.eddnav.ifb.fluid.persistence.OutputDAO
import com.eddnav.ifb.fluid.persistence.OutputRow
import com.eddnav.ifb.patient.persistence.PatientDAO
import com.eddnav.ifb.patient.persistence.PatientRow
import com.eddnav.ifb.report.persistence.ReportDAO
import com.eddnav.ifb.report.persistence.ReportRow
import com.eddnav.ifb.surgery.persistence.SurgeryDAO
import com.eddnav.ifb.surgery.persistence.SurgeryRow

/**
 * @author Eduardo Naveda
 */
@Database(entities = [PatientRow::class, SurgeryRow::class, IntakeRow::class, OutputRow::class, ReportRow::class], version = 1, exportSchema = false)
abstract class IFBDatabase : RoomDatabase() {
    abstract fun patientDAO(): PatientDAO
    abstract fun surgeryDAO(): SurgeryDAO
    abstract fun intakeDAO(): IntakeDAO
    abstract fun outputDAO(): OutputDAO
    abstract fun reportDAO(): ReportDAO
}