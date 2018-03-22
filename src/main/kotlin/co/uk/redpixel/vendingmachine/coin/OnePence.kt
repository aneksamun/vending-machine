package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class OnePence : Coin(1, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
