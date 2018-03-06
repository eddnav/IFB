package com.eddnav.ifb.report

import com.eddnav.ifb.domain.report.HydrationSchedule
import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import com.eddnav.ifb.domain.report.Report
import com.eddnav.ifb.domain.surgery.Surgery
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author  Eduardo Naveda
 */
class ReportSpec : SubjectSpek<Report>({
    given("a report") {
        val intake = Intake(2.3, 4.2, 3.2, 3.4)
        val output = Output(4.3, 1.2, 2.2, 1.5)
        val patient = Patient("Pat", "Noobie", 44.3, "m",
                60.0, 2.0, 2, 12.0,
                5.0, intake, output)
        val hourlyHydration = HydrationSchedule(patient)
        val surgery = Surgery("Some procedure", 3.0)
        subject {
            Report(0, patient, surgery, hourlyHydration)
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
