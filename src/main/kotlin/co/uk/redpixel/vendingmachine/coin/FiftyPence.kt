package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class FiftyPence : Coin(50, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
