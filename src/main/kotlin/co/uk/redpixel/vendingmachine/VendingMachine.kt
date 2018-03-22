package co.uk.redpixel.vendingmachine

import co.uk.redpixel.vendingmachine.coin.Change
import co.uk.redpixel.vendingmachine.common.UnsupportedInventory
import co.uk.redpixel.vendingmachine.supply.Coins
import co.uk.redpixel.vendingmachine.supply.Inventory
import co.uk.redpixel.vendingmachine.supply.Packs

interface VendingMachine {

    infix fun dispense(amount: Int): Change

    companion object {
        fun <T> on(inventory: Inventory<T>): VendingMachine {
            return when (inventory) {
                is Coins -> CoinsVendingMachine(inventory as Coins)
                is Packs -> PackVendingMachine(inventory as Packs)
                else -> throw UnsupportedInventory(inventory)
            }
        }
    }
}
