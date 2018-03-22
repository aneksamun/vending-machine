package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.*

class Coins : Inventory<Coin> {

    private val items = listOf(
            OnePence(),
            TwoPence(),
            FivePence(),
            TenPence(),
            TwentyPence(),
            FiftyPence(),
            OnePound()
    )

    override fun get(index: Int): Coin {
        return items[index]
    }

    override fun size(): Int {
        return items.size
    }

    override fun iterator(): Iterator<Coin> {
        return items.iterator()
    }
}
