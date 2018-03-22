package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBX

class TenPence : Coin(10, GBX) {

    override fun toString(): String {
        return "$denomination${currency.sign}"
    }
}
