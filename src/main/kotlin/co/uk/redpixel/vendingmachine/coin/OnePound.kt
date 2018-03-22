package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.coin.Currency.GBP

class OnePound : Coin(100, GBP) {

    override fun toString(): String {
        return "${currency.sign}${denomination/100}"
    }
}
