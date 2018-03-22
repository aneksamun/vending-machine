package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class PropertyStockSpec : Spek({

    describe("load inventory") {
        on("load") {
            val inventory = PropertyStock.load()

            it("reads properties") {
                assertTrue(
                        inventory[0] == Pack(FivePence(), 200) &&
                                inventory[1] == Pack(FiftyPence(), 24) &&
                                inventory[2] == Pack(TwoPence(), 11) &&
                                inventory[3] == Pack(OnePence(), 23) &&
                                inventory[4] == Pack(TenPence(), 99) &&
                                inventory[5] == Pack(OnePound(), 11) &&
                                inventory[6] == Pack(TwentyPence(), 0))
            }
        }
    }
})
