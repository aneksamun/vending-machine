package co.uk.redpixel.vendingmachine

import co.uk.redpixel.vendingmachine.coin.Change
import co.uk.redpixel.vendingmachine.coin.Coin
import co.uk.redpixel.vendingmachine.common.InsufficientCoinageException
import co.uk.redpixel.vendingmachine.common.array2dOfInt
import co.uk.redpixel.vendingmachine.common.init
import co.uk.redpixel.vendingmachine.supply.Packs

class PackVendingMachine(private val packs: Packs) : VendingMachine {

    override fun dispense(amount: Int): Change {
        when {
            amount >= 1 -> {
                val table = IntArray(amount + 1)
                val coins = arrayOfNulls<Coin>(amount + 1)
                val copy = array2dOfInt(amount + 1, packs.size()).init(0, {
                    packs[it].size
                })

                for (denomination in 1..amount) {
                    var totalRequired = Int.MAX_VALUE
                    table[denomination] = totalRequired

                    for (packIndex in 0 until packs.size()) {
                        val coin = packs[packIndex].coin
                        val previous = denomination - coin.denomination

                        if (denomination >= coin.denomination &&
                                table[previous] < Integer.MAX_VALUE &&
                                copy[previous][packIndex] > 0) {

                            totalRequired = 1 + table[previous]

                            if (table[denomination] > totalRequired) {
                                table[denomination] = totalRequired
                                coins[denomination] = coin
                                copy[denomination][packIndex] = copy[previous][packIndex] - 1
                            } else {
                                copy[denomination][packIndex] = copy[previous][packIndex]
                            }

                        } else if (denomination < coin.denomination) {
                            copy[denomination][packIndex] = copy[0][packIndex]
                        }
                    }
                }

                if (table[amount] == Integer.MAX_VALUE)
                    throw InsufficientCoinageException()

                val change = findOptimalChange(amount, coins)
                updateInventory(change, copy)
                return change
            }
            else -> return Change.empty()
        }
    }

    private fun findOptimalChange(current: Int, pennies: Array<Coin?>): Change {
        return if (current > 0) {
            val coin = pennies[current]!!
            val coins = findOptimalChange(current - coin.denomination, pennies)
            coins.merge(coin)
            coins
        } else Change.empty()
    }

    private fun updateInventory(change: Change, copy: Array<IntArray>) {
        change.forEach { coin, size ->
            for (packIndex in 0 until packs.size()) {
                if (packs[packIndex].coin == coin) {
                    val remainingSizeIndex = coin.denomination * size
                    val remainingSize = copy[remainingSizeIndex][packIndex]
                    packs[packIndex] = packs[packIndex].copy(size = remainingSize)
                    break
                }
            }
        }
        packs.store()
    }
}
