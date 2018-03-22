package co.uk.redpixel.vendingmachine.common

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class ArrayExtensionsSpec : Spek({

    describe("create 2d array") {
        on("create") {
            val outerSize = 1
            val innerSize = 1

            val array = array2dOfInt(outerSize, innerSize)

            it("creates an array with specified dimensions") {
                assertTrue(array.size == outerSize && array[0].size == innerSize)
            }
        }
    }

    describe("init 2d array") {
        on("init at certain position") {
            val array = array2dOfInt(3, 7).init(0, { i -> i })

            it("sets provided values") {
                (0 until 7).forEach { i ->
                    assertTrue { array[0][i] == i }
                }
            }
        }
    }
})
