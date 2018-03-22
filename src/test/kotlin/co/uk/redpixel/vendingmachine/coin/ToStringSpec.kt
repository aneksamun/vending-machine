package co.uk.redpixel.vendingmachine.coin

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class ToStringSpec : Spek({

    given("packs of coins") {
        val packs = mapOf(
                Pack(OnePence(), 10) to "1px10",
                Pack(TwoPence(), 5) to "2px5",
                Pack(TenPence(), 3) to "10px3",
                Pack(TwentyPence(), 7) to "20px7",
                Pack(FiftyPence(), 11) to "50px11",
                Pack(OnePound(), 100) to "£2x100"
        )
        describe("calling to string") {
            on("call") {
                packs.forEach { pack ->
                    it("prints expected string") {
                        assertTrue { pack.key.toString() == pack.value }
                    }
                }
            }
        }
    }
})
