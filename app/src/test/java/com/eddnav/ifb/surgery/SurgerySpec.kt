package com.eddnav.ifb.surgery

import org.amshove.kluent.shouldEqualTo
import org.amshove.kluent.shouldThrow
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.jetbrains.spek.subject.SubjectSpek

/**
 * @author  Eduardo Naveda
 */
class SurgerySpec : SubjectSpek<Surgery>({
    given("a surgery") {
        subject {
            Surgery("Some procedure", 4.0)
        }
        on("setting duration") {
            it("should succeed if value is greater than 0") {
                subject.duration = 1.0
                subject.duration shouldEqualTo 1.0
            }
            it("should fail if value is 0") {
                { subject.duration = 0.0 } shouldThrow IllegalArgumentException::class
            }
            it("should fail if value is less than 0") {
                { subject.duration = -10.0 } shouldThrow IllegalArgumentException::class
            }
        }
    }
})