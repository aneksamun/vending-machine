package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.common.NotEnoughBalanceException
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CoinsSubtractSpec : Spek({

    describe("subtract coins from the pack") {
        val pounds = Pack(OnePound(), 10)

        on("subtract") {
            val remaining = pounds - 3

            it("should subsctract coins from pack") {
                assertEquals(7, remaining.size)
            }
        }
    }

    describe("not enough balance to subtract") {
        val pennies = Pack(OnePence(), 0)

        on("coins subtract") {
            assertFailsWith<NotEnoughBalanceException> { pennies - 1 }
        }
    }

    describe("decrement size of pack") {
        var pennies = Pack(TwentyPence(), 4)

        on("decrement") {
            pennies--

            it("should decrement size of coins") {
                assertEquals(3, pennies.size)
            }
        }
    }

    describe("not enough balance to decrement") {
        var pennies = Pack(FivePence(), 0)

        on("coins decrease") {
            assertFailsWith<NotEnoughBalanceException> { pennies-- }
        }
    }
})
