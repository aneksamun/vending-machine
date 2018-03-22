package co.uk.redpixel.vendingmachine.coin

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class CoinsAddSpec : Spek({

    describe("add extra coins to a pack") {
        val pennies = Pack(FiftyPence(), 19)

        on("add") {
            val addition = pennies + 1

            it("should increase pack size") {
                assertEquals(20, addition.size)
            }
        }
    }

    describe("increment size of coin pack") {
        var pennies = Pack(TenPence(), 1)

        on("increment") {
            pennies++

            it("should increment the size of coins") {
                assertEquals(2, pennies.size)
            }
        }
    }
})
