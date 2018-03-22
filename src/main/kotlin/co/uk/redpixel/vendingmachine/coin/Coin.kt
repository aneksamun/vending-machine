package co.uk.redpixel.vendingmachine.coin

import java.util.Objects

abstract class Coin protected constructor(val denomination: Int, val currency: Currency) : Comparable<Coin> {

    override fun compareTo(other: Coin): Int {
        return this.denomination.compareTo(other.denomination)
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) {
            return true
        }
        if (other !is Coin) {
            return false
        }
        return denomination == other.denomination &&
                currency == other.currency
    }

    override fun hashCode(): Int {
        return Objects.hash(denomination, currency)
    }
}
