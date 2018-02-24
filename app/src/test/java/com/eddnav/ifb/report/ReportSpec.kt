package com.eddnav.ifb.report

import com.eddnav.ifb.fluid.HourlyHydration
import com.eddnav.ifb.fluid.Intake
import com.eddnav.ifb.fluid.Output
import com.eddnav.ifb.patient.Patient
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author  Eduardo Naveda
 */
class ReportSpec : SubjectSpek<Report>({
    given("a report") {
        val patient = Patient("Pat", "Noobie", 44.3, "m",
                bloodVolume = 60.0, fasting = 2.0, surgicalStress = 2, hemoglobin = 12.0,
                minHemoglobin = 5.0)
        val hourlyHydration = HourlyHydration(patient)
        val intake = Intake(2.3, 4.2, 3.2, 3.4)
        val output = Output(4.3, 1.2, 2.2, 1.5)
        subject {
            Report(patient, intake, output, hourlyHydration, 3.0)
        }
        on("setting surgery duration") {
            it("should succeed if value is greater than 0") {
                subject.surgeryDuration = 1.0
                subject.surgeryDuration shouldEqualTo 1.0
            }
            it("should fail if value is 0") {
                { subject.surgeryDuration = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.surgeryDuration = -10.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("getting minimum allowable blood loss") {
            it("should calculate it for the given patient info input") {
                subject.minimumAllowableBloodLoss shouldEqualTo (7.0 / 12.0) * 44.3 * 60.0
            }
        }
        on("getting hourly diuresis") {
            it("should calculate it for the given fluid output input") {
                subject.hourlyDiuresis shouldEqualTo 4.3 / 3.0
            }
        }
        on("getting urine output") {
            it("should calculate it for the given patient info input") {
                subject.urineOutput shouldEqualTo 4.3 / 3.0 / 44.3
            }
        }
        on("getting intake supply") {
            it("should calculate it for the given input") {
                subject.intakeSupply shouldEqualTo 13.1 / 44.3
            }
        }
        on("getting final fluid balance") {
            it("should calculate it for the given intake/output input") {
                subject.finalFluidBalance shouldEqualTo 13.1 - 9.2
            }
        }
    }
})
