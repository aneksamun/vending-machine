package co.uk.redpixel.vendingmachine.supply

import co.uk.redpixel.vendingmachine.coin.*
import co.uk.redpixel.vendingmachine.common.InvalidCoinDenominationException
import java.io.FileOutputStream
import java.lang.Thread.currentThread
import java.util.*

object PropertyStock : Stock {

    private const val propertyFile = "inventory.properties"

    override fun load() = readProperties().map {
        val denomination = toInt(it.key)
        val size = toInt(it.value)
        val coin = when (denomination) {
            1 -> OnePence()
            2 -> TwoPence()
            5 -> FivePence()
            10 -> TenPence()
            20 -> TwentyPence()
            50 -> FiftyPence()
            100 -> OnePound()
            else -> throw InvalidCoinDenominationException(denomination)
        }
        Pack(coin, size)
    }

    override fun update(inventory: List<Pack>) {
        FileOutputStream(propertyFile).use { stream ->
            val properties = Properties()
            inventory.forEach { properties[it.coin.denomination.toString()] = it.size.toString() }
            properties.store(stream, null)
        }
    }

    private fun readProperties() = Properties().apply {
        currentThread().contextClassLoader.getResourceAsStream(propertyFile).use { stream ->
            load(stream)
        }
    }

    private fun toInt(value: Any): Int {
        val string = value as String
        return string.toInt()
    }
}
