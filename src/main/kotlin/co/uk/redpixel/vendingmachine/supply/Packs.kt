package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.Pack

class Packs(private val stock: Stock) : Inventory<Pack> {

    private val coinPackList: MutableList<Pack>

    init {
        coinPackList = loadFromStock()
    }

    override operator fun get(index: Int): Pack {
        return coinPackList[index]
    }

    operator fun set(index: Int, pack: Pack) {
        coinPackList[index] = pack
    }

    override fun size(): Int {
        return coinPackList.size
    }

    override fun iterator(): Iterator<Pack> {
        return coinPackList.iterator()
    }

    fun store() {
        stock.update(coinPackList)
    }

    private fun loadFromStock(): MutableList<Pack> {
        return stock.load().sortedBy { it.coin }.toMutableList()
    }
}
