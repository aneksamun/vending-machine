package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class TwoPence : Coin(2, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
