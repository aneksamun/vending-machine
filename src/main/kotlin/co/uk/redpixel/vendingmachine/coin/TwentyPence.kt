package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class TwentyPence : Coin(20, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
