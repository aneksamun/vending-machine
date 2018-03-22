package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.Pack

interface Stock {
    fun load(): List<Pack>
    fun update(inventory: List<Pack>)
}
