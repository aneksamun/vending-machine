package co.uk.redpixel.vendingmachine.coin

import co.uk.redpixel.vendingmachine.common.NotEnoughBalanceException

data class Pack(val coin: Coin, val size: Int) {

    operator fun minus(decrement: Int) : Pack {
        if (decrement > size) throw NotEnoughBalanceException()
        return this.copy(size = this.size - decrement)
    }

    operator fun dec() : Pack {
        if (this.size < 1) throw NotEnoughBalanceException()
        return this.copy(size = this.size - 1)
    }

    operator fun plus(increment: Int) : Pack {
        return this.copy(size = this.size + increment)
    }

    operator fun inc() : Pack {
        return this.copy(size = this.size + 1)
    }

    override fun toString(): String {
        return "${coin}x$size"
    }
}
