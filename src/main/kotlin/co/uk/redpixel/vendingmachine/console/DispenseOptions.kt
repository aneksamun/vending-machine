package co.uk.redpixel.vendingmachine.console

import co.uk.redpixel.vendingmachine.supply.Packs
import co.uk.redpixel.vendingmachine.supply.Coins
import co.uk.redpixel.vendingmachine.supply.PropertyStock
import com.xenomachina.argparser.ArgParser

class DispenseOptions(parser: ArgParser) {
    val craftInventory by parser.mapping("" +
            "--limited" to { Packs(PropertyStock) },
            "--unlimited" to { Coins() },
            help = "determines type of inventory being hold by vending machine"
    )
}
