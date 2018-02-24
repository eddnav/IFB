package com.eddnav.ifb.fluid

import com.eddnav.ifb.patient.Patient
import org.amshove.kluent.shouldEqualTo
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author Eduardo Naveda
 */
object HourlyHydrationSpec: SubjectSpek<HourlyHydration>({
    given("hourly hydration schedule") {
        val patient = Patient("Pat", "Noobie", 5.0, "m",
                bloodVolume = 50.0, fasting = 5.0, surgicalStress = 9, hemoglobin = 1.0,
                minHemoglobin = 2.0)
        subject { HourlyHydration(patient) }
        on("getting base hydration schedule") {
            it("should be an array describing 4 hours") {
                subject.base.size shouldEqualTo 4
            }
            it("should have the calculated first hour") {
                subject.base[0] shouldEqualTo 10.0
            }
            it("should have the calculated second hour") {
                subject.base[1] shouldEqualTo 10.0
            }
            it("should have the calculated third hour") {
                subject.base[2] shouldEqualTo 10.0
            }
            it("should have the calculated fourth hour") {
                subject.base[3] shouldEqualTo 10.0
            }
        }
        on("getting insensible losses hydration schedule") {
            it("should be an array describing 4 hours") {
                subject.insensibleLosses.size shouldEqualTo 4
            }
            it("should have the calculated first hour") {
                subject.insensibleLosses[0] shouldEqualTo 10.0
            }
            it("should have the calculated second hour") {
                subject.insensibleLosses[1] shouldEqualTo 10.0
            }
            it("should have the calculated third hour") {
                subject.insensibleLosses[2] shouldEqualTo 10.0
            }
            it("should have the calculated fourth hour") {
                subject.insensibleLosses[3] shouldEqualTo 10.0
            }
        }
        on("getting pathological fasting hydration schedule") {
            it("should be an array describing 3 hours") {
                subject.fasting.size shouldEqualTo 4
            }
            it("should have the calculated first hour") {
                subject.fasting[0] shouldEqualTo 25.0
            }
            it("should have the calculated second hour") {
                subject.fasting[1] shouldEqualTo 12.5
            }
            it("should have the calculated third hour") {
                subject.fasting[2] shouldEqualTo 12.5
            }
            it("should have the calculated fourth hour") {
                subject.fasting[3] shouldEqualTo 0.0
            }
        }
        on("getting surgical stress hydration schedule") {
            it("should be an array describing 4 hours") {
                subject.surgicalStress.size shouldEqualTo 4
            }
            it("should have the calculated first hour") {
                subject.surgicalStress[0] shouldEqualTo 45.0
            }
            it("should have the calculated second hour") {
                subject.surgicalStress[1] shouldEqualTo 45.0
            }
            it("should have the calculated third hour") {
                subject.surgicalStress[2] shouldEqualTo 45.0
            }
            it("should have the calculated fourth hour") {
                subject.surgicalStress[3] shouldEqualTo 45.0
            }
        }
        on("getting total hydration schedule") {
            it("should be an array describing 4 hours") {
                subject.total.size shouldEqualTo 4
            }
            it("should have the calculated first hour") {
                subject.total[0] shouldEqualTo 90.0
            }
            it("should have the calculated second hour") {
                subject.total[1] shouldEqualTo 77.5
            }
            it("should have the calculated third hour") {
                subject.total[2] shouldEqualTo 77.5
            }
            it("should have the calculated fourth hour") {
                subject.total[3] shouldEqualTo 65.0
            }
        }
    }
})