package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.*
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PacksSpec : Spek({

    val stock = mock<Stock> {
        on { load() } doReturn listOf(
                Pack(OnePence(), 10),
                Pack(TwoPence(), 20),
                Pack(FiftyPence(), 30)
        )
    }

    val packs = Packs(stock)

    describe("get size") {
        on("acquiring size") {
            it("returns expected value") {
                assertEquals(3, packs.size())
            }
        }
    }

    describe("get pack by index") {
        on("acquiring pack") {
            it("returns expected item") {
                assertEquals(Pack(TwoPence(), 20), packs[1])
            }
        }
    }

    describe("replace pack of coins") {
        on("replacing pack") {
            val index = 1
            val replacement = Pack(OnePound(), 100000)
            packs[index] = replacement

            it("substitutes an item") {
                assertTrue { packs[index] == replacement }
            }
        }
    }

    describe("store coin packs") {
        on("storing coins") {
            packs.store()

            it("writes to stock") {
                verify(stock).update(any())
            }
        }
    }
})
