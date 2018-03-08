package com.eddnav.ifb.cache.report

/**
 * @author Eduardo Naveda
 */
object Constants {

    internal const val QUERY_GET_REPORT = "SELECT Reports.id AS id, firstName, lastName, weight, sex, bloodVolume, " +
            "fasting, surgicalStress, hemoglobin, minHemoglobin, crystalloids, " +
            "colloids, hemoderivatives, drugInfusions, diuresis, aspiration, " +
            "compresses, levinsTube, description, duration FROM Reports INNER JOIN Surgeries " +
            "on Reports.surgeryId = Surgeries.id INNER JOIN (SELECT Patients.id, firstName, " +
            "lastName, weight, sex, bloodVolume, fasting, surgicalStress, hemoglobin, " +
            "minHemoglobin, crystalloids, colloids, hemoderivatives, drugInfusions, diuresis, " +
            "aspiration, compresses, levinsTube from Patients INNER JOIN Intakes on " +
            "Patients.intakeId = Intakes.id INNER JOIN Outputs on Patients.outputId = Outputs.id) " +
            "P on P.id = Reports.patientId WHERE Reports.id = :id"

    internal const val QUERY_GET_REPORTS = "SELECT Reports.id AS id, firstName, lastName, weight, sex, bloodVolume, " +
            "fasting, surgicalStress, hemoglobin, minHemoglobin, crystalloids, " +
            "colloids, hemoderivatives, drugInfusions, diuresis, aspiration, " +
            "compresses, levinsTube, description, duration FROM Reports INNER JOIN Surgeries " +
            "on Reports.surgeryId = Surgeries.id INNER JOIN (SELECT Patients.id, firstName, " +
            "lastName, weight, sex, bloodVolume, fasting, surgicalStress, hemoglobin, " +
            "minHemoglobin, crystalloids, colloids, hemoderivatives, drugInfusions, diuresis, " +
            "aspiration, compresses, levinsTube from Patients INNER JOIN Intakes on " +
            "Patients.intakeId = Intakes.id INNER JOIN Outputs on Patients.outputId = Outputs.id) " +
            "P on P.id = Reports.patientId"
}