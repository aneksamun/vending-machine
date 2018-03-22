package co.uk.redpixel.vendingmachine.supply

interface Inventory<out T> : Iterable<T> {
    operator fun get(index: Int): T
    fun size(): Int
}
