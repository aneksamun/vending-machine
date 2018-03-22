package co.uk.redpixel.vendingmachine.coin

import java.util.LinkedHashMap

class Change private constructor() : LinkedHashMap<Coin, Int>() {

    fun merge(coin: Coin) {
        this.merge(coin, 1, { current, _ -> current + 1 })
    }

    fun total(): Int = values.fold(0, {
        current, next -> current + next
    })

    override fun toString(): String {
        return entries.joinToString(separator = ", ", transform = {
            entry -> "${entry.key}x${entry.value}"
        })
    }

    companion object {
        fun empty(): Change {
            return Change()
        }

        fun of(vararg entries: Pair<Coin, Int>) : Change {
            val change = Change()
            for (entry in entries) {
                change[entry.first] = entry.second
            }
            return change
        }
    }
}
