package com.eddnav.ifb.cache.report

/**
 * @author Eduardo Naveda
 */
object Constants {

    internal const val QUERY_GET_REPORT = "SELECT id, firstName, lastName, age, weight, sex, bloodVolume, " +
            "fasting, surgicalStress, hemoglobin, minHemoglobin, crystalloids, " +
            "colloids, hemoderivatives, drugInfusions, diuresis, aspiration, " +
            "compresses, levinsTube, description, duration, created, updated FROM Reports " +
            "WHERE Reports.id = :id"

    internal const val QUERY_GET_REPORTS = "SELECT id, firstName, lastName, age, weight, sex, bloodVolume, " +
            "fasting, surgicalStress, hemoglobin, minHemoglobin, crystalloids, " +
            "colloids, hemoderivatives, drugInfusions, diuresis, aspiration, " +
            "compresses, levinsTube, description, duration, created, updated FROM Reports"
}