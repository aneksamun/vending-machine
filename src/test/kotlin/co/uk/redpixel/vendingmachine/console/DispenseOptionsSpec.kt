package co.uk.redpixel.vendingmachine.console

import co.uk.redpixel.vendingmachine.supply.Coins
import co.uk.redpixel.vendingmachine.supply.Packs
import com.xenomachina.argparser.ArgParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertTrue

class DispenseOptionsSpec : Spek({

    given("inventory options") {
        val options = mapOf(
                arrayOf("--limited") to Packs::class,
                arrayOf("--unlimited") to Coins::class
        )
        describe("parsing arguments") {
            on("parse") {
                options.forEach { option ->
                    val parser = ArgParser(option.key)

                    it("option") {
                        DispenseOptions(parser).run {
                            assertTrue { craftInventory()::class == option.value }
                        }
                    }
                }
            }
        }
    }
})
