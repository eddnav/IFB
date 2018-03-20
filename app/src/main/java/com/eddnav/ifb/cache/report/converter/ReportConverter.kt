package com.eddnav.ifb.cache.report.converter

import com.eddnav.ifb.cache.report.model.ReportEntity
import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.HydrationSchedule
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.domain.surgery.Surgery

/**
 * @author Eduardo Naveda
 */
class ReportConverter {

    companion object {

        fun fromDomain(report: Report): ReportEntity {
            return ReportEntity(
                    report.id,
                    report.patient.firstName,
                    report.patient.lastName,
                    report.patient.age,
                    report.patient.weight,
                    report.patient.sex,
                    report.patient.bloodVolume,
                    report.patient.fasting,
                    report.patient.surgicalStress,
                    report.patient.hemoglobin,
                    report.patient.minHemoglobin,
                    report.surgery.description,
                    report.surgery.duration,
                    report.patient.intake.crystalloids,
                    report.patient.intake.colloids,
                    report.patient.intake.hemoderivatives,
                    report.patient.intake.drugInfusions,
                    report.patient.output.diuresis,
                    report.patient.output.aspiration,
                    report.patient.output.compresses,
                    report.patient.output.levinsTube,
                    report.created,
                    report.updated)
        }

        fun toDomain(entity: ReportEntity): Report {
            val intake = Intake(entity.crystalloids, entity.colloids, entity.hemoderivatives, entity.drugInfusions)
            val output = Output(entity.diuresis, entity.aspiration, entity.compresses, entity.levinsTube)
            val surgery = Surgery(entity.description, entity.duration)
            val patient = Patient(entity.firstName, entity.lastName, entity.age, entity.weight,
                    entity.sex, entity.bloodVolume, entity.fasting, entity.surgicalStress,
                    entity.hemoglobin, entity.minHemoglobin, intake, output)

            return Report(entity.id, patient, surgery, HydrationSchedule(patient), entity.created, entity.updated)
        }
    }
}