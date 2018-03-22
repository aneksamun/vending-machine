package co.uk.redpixel.vendingmachine.console

import com.xenomachina.argparser.HelpFormatter
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

class DispenseHelpFormatterSpec : Spek({

    describe("display usage") {
        val formatter = DispenseHelpFormatter()

        on("gathering options") {
            val actual = formatter.format("vending-machine", 100,
                    listOf(HelpFormatter.Value(
                            usages = listOf("--limited", "--unlimited"),
                            isRequired = true,
                            isPositional = false,
                            isRepeating = false,
                            help = "determines type of inventory being hold by vending machine")))

            it("should match expected output") {
                assertEquals("" +
                        "usage: vending-machine (--limited|--unlimited)\n\n" +
                        "required arguments\n" +
                        "  --limited,    determines type of inventory being hold by vending machine\n" +
                        "  --unlimited\n",
                        actual
                )
            }
        }
    }
})