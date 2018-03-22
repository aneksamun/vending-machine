package co.uk.redpixel.vendingmachine.integration

import co.uk.redpixel.vendingmachine.CoinsVendingMachine
import co.uk.redpixel.vendingmachine.coin.*
import co.uk.redpixel.vendingmachine.supply.Coins
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class CoinsVendingMachineSpec : Spek({

    val machine = CoinsVendingMachine(Coins())

    describe("invalid amount") {
        val amounts = listOf(0, -1)

        on("dispense amount") {
            amounts.forEach { amount ->
                it("returns no change") {
                    assertTrue { machine.dispense(amount).isEmpty() }
                }
            }
        }
    }

    describe("dispense coins") {
        val table = mapOf(
                8 to Change.of(
                        OnePence() to 1,
                        TwoPence() to 1,
                        FivePence() to 1
                ),
                74 to Change.of(
                        FiftyPence() to 1,
                        TwentyPence() to 1,
                        FivePence() to 1,
                        TwoPence() to 2
                ),
                300 to Change.of(OnePound() to 3)
        )

        on("dispense") {
            table.forEach { item ->
                val change = machine dispense item.key

                it("returns expected change") {
                    assertTrue {
                        change == item.value
                    }
                }
            }
        }
    }
})