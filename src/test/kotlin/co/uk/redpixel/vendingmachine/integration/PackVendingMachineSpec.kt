package co.uk.redpixel.vendingmachine.integration

import co.uk.redpixel.vendingmachine.PackVendingMachine
import co.uk.redpixel.vendingmachine.coin.*
import co.uk.redpixel.vendingmachine.supply.Packs
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertFails
import kotlin.test.assertTrue

class PackVendingMachineSpec : Spek({

    val packs = Packs(mock {
        on { load() } doReturn listOf(
                Pack(OnePence(), 10),
                Pack(TwoPence(), 20),
                Pack(FiftyPence(), 30),
                Pack(OnePound(), 4)
        )
    })

    val machine = PackVendingMachine(packs)

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

    describe("insufficient coinage") {
        on("dispense insufficient amount") {
            it("throws exception") {
                assertFails { machine.dispense(5000) }
            }
        }
    }

    describe("dispense coins") {
        val table = mapOf(
                300 to Change.of(OnePound() to 3),
                57 to Change.of(
                        FiftyPence() to 1,
                        FivePence() to 1,
                        TwoPence() to 2
                ),
                9 to Change.of(
                        FivePence() to 1,
                        TwoPence() to 2
                )
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

    describe("check mod") {
        on("dispense") {
            val change = machine dispense 100

            it("returns expected change") {
                assertTrue {
                    change == Change.of(OnePound() to 1) &&
                            packs[3].size == 0
                }
            }
        }
    }
})
