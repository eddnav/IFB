package com.eddnav.ifb.fluid

import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author Eduardo Naveda
 */
object IntakeSpec : SubjectSpek<Intake>({
    given("an intake", {
        subject {
            Intake(1.0, 3.0, 4.0, 3.0)
        }
        on("setting crystalloids") {
            it("should succeed if value is greater than 0") {
                subject.crystalloids = 1.0
                subject.crystalloids shouldEqualTo 1.0
            }
            it("should succeed if value is equals to 0") {
                subject.crystalloids = 0.0
                subject.crystalloids shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.crystalloids = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting colloids") {
            it("should succeed if value is greater than or equals to 0") {
                subject.colloids = 2.0
                subject.colloids shouldEqualTo 2.0
            }
            it("should succeed if value is equals to 0") {
                subject.colloids = 0.0
                subject.colloids shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.colloids = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting hemoderivatives") {
            it("should succeed if value is greater than or equals to 0") {
                subject.hemoderivatives = 0.3
                subject.hemoderivatives shouldEqualTo 0.3
            }
            it("should succeed if value is equals to 0") {
                subject.hemoderivatives = 0.0
                subject.hemoderivatives shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.hemoderivatives = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting drug infusions") {
            it("should succeed if value is greater than or equals to 0") {
                subject.drugInfusions = 3.0
                subject.drugInfusions shouldEqualTo 3.0
            }
            it("should succeed if value is equals to 0") {
                subject.drugInfusions = 0.0
                subject.drugInfusions shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.drugInfusions = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("getting total") {
            it("should be the sum of all other intake values") {
                subject.total shouldEqualTo 11.0
            }
        }
    })
})