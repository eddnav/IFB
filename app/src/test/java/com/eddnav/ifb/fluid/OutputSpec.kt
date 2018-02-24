package com.eddnav.ifb.fluid

import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author Eduardo Naveda
 */
object OutputSpec : SubjectSpek<Output>({
    given("an output", {
        subject {
            Output(21.0, 3.4, 2.0, 9.0)
        }
        on("setting crystalloids") {
            it("should succeed if value is greater than 0") {
                subject.diuresis = 1.0
                subject.diuresis shouldEqualTo 1.0
            }
            it("should succeed if value is equals to 0") {
                subject.diuresis = 0.0
                subject.diuresis shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.diuresis = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting colloids") {
            it("should succeed if value is greater than 0") {
                subject.aspiration = 1.0
                subject.aspiration shouldEqualTo 1.0
            }
            it("should succeed if value is equals to 0") {
                subject.aspiration = 0.0
                subject.aspiration shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.aspiration = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting compresses") {
            it("should succeed if value is greater than 0") {
                subject.compresses = 1.0
                subject.compresses shouldEqualTo 1.0
            }
            it("should succeed if value is equals to 0") {
                subject.compresses = 0.0
                subject.compresses shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.compresses = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("setting Levin's tube") {
            it("should succeed if value is greater than 0") {
                subject.levinsTube = 1.0
                subject.levinsTube shouldEqualTo 1.0
            }
            it("should succeed if value is equals to 0") {
                subject.levinsTube = 0.0
                subject.levinsTube shouldEqualTo 0.0
            }
            it("should fail if value is less than 0") {
                { subject.levinsTube = -1.0 } shouldThrow IllegalArgumentException::class
            }
        }
        on("getting total") {
            it("should be the sum of all other output values") {
                subject.total shouldEqualTo 35.4
            }
        }
    })
})