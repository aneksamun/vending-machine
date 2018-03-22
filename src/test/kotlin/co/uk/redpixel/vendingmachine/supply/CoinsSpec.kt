package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.TwoPence
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class CoinsSpec : Spek({

    val coins = Coins()

    describe("get size") {
        on("acquiring size") {
            it("returns expected value") {
                assertEquals(7, coins.size())
            }
        }
    }

    describe("get coin by index") {
        on("acquiring coin") {
            it("returns expected coin") {
                assertEquals(TwoPence(), coins[1])
            }
        }
    }
})
