package co.uk.redpixel.vendingmachine.coin

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class CompareCoinSpec : Spek({

    given("a coin pairs") {
        val pairs = listOf(
                Pair(OnePence(), TwoPence()),
                Pair(TwoPence(), FivePence()),
                Pair(FivePence(), TenPence()),
                Pair(TenPence(), TwentyPence()),
                Pair(TwentyPence(), FiftyPence()),
                Pair(FiftyPence(), OnePound())
        )

        describe("compare coins") {
            on("comparing coins") {
                pairs.forEach { pair ->
                    it("determines coin of ${pair.second} to be greater than ${pair.first}") {
                        assertTrue { pair.second > pair.first }
                    }
                }
            }
        }
    }
})
