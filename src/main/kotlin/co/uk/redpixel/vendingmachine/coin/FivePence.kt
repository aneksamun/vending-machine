package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class FivePence : Coin(5, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
