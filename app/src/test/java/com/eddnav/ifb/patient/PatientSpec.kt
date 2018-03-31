package com.eddnav.ifb.patient

import com.eddnav.ifb.domain.intake.Intake
import com.eddnav.ifb.domain.output.Output
import com.eddnav.ifb.domain.patient.Patient
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author  Eduardo Naveda
 */
object PatientSpec : SubjectSpek<Patient>({
    given("a patient", {
        val intake = Intake(2.3, 4.2, 3.2, 3.4)
        val output = Output(4.3, 1.2, 2.2, 1.5)
        subject {
            Patient("Pat", "Noobie", 10, 1.0,
                    "m", 60.0, 0.0, 0,
                    2.0, 1.0, intake, output)
        }
        on("getting full name") {
            it("should return first and last name, concatenated by a space") {
                subject.fullName shouldEqual "Pat Noobie"
            }
        }
        on("setting age") {
            it("should succeed if value is greater than 0") {
                subject.age = 11
                subject.age shouldEqualTo 11
            }
            it("should fail if value is equals to 0") {
                { subject.age = 0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.age = -2 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting weight") {
            it("should succeed if value is greater than 0") {
                subject.bloodVolume = 2.0
                subject.bloodVolume shouldEqualTo 2.0
            }
            it("should fail if value is equals to 0") {
                { subject.weight = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.weight = -2.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting sex") {
            it("should succeed if value is equal to \"${Patient.SEX_FEMALE}\"") {
                subject.sex = Patient.SEX_FEMALE
                subject.sex shouldBeEqualTo Patient.SEX_FEMALE
            }
            it("should succeed if value is equal to \"${Patient.SEX_MALE}\"") {
                subject.sex = Patient.SEX_MALE
                subject.sex shouldBeEqualTo Patient.SEX_MALE
            }
            it("should fail if value is different than \"${Patient.SEX_FEMALE}\" or \"${Patient.SEX_MALE}\"") {
                { subject.sex = "clearly not a sex value" } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting blood volume") {
            it("should succeed if value is greater than 0") {
                subject.bloodVolume = 11.0
                subject.bloodVolume shouldEqualTo 11.0
            }
            it("should fail if value is equals to 0") {
                { subject.bloodVolume = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.bloodVolume = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting fasting hours") {
            it("should succeed if value is greater than 0") {
                subject.fasting = 1.2
                subject.fasting shouldEqualTo 1.2
            }
            it("should succeed if value is equals to 0") {
                subject.fasting = 0.0
                subject.fasting shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.fasting = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting surgical stress") {
            it("should succeed if value is greater than 0") {
                subject.surgicalStress = 3
                subject.surgicalStress shouldEqualTo 3
            }
            it("should succeed if value is equals to 0") {
                subject.surgicalStress = 0
                subject.surgicalStress shouldEqualTo 0
            }
            it("should succeed if value is equals to max surgical stress") {
                subject.surgicalStress = Patient.MAX_SURGICAL_STRESS
                subject.surgicalStress shouldEqualTo Patient.MAX_SURGICAL_STRESS
            }
            it("should fail if value is less than 0") {
                { subject.surgicalStress = -1 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is greater than max surgical stress") {
                { subject.surgicalStress = Patient.MAX_SURGICAL_STRESS + 1 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting hemoglobin") {
            it("should succeed if value is greater than 0") {
                subject.hemoglobin = 3.2
                subject.hemoglobin shouldEqualTo 3.2
            }
            it("should fail if value is equals to 0") {
                { subject.hemoglobin = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.hemoglobin = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting minimum hemoglobin") {
            it("should succeed if value is greater than 0") {
                subject.minHemoglobin = 3.4
                subject.minHemoglobin shouldEqualTo 3.4
            }
            it("should fail if value is equals 0") {
                { subject.minHemoglobin = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.minHemoglobin = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }

    })
})