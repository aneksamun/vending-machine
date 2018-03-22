package co.uk.redpixel.vendingmachine

import co.uk.redpixel.vendingmachine.common.InsufficientCoinageException
import co.uk.redpixel.vendingmachine.common.NotEnoughBalanceException
import co.uk.redpixel.vendingmachine.console.DispenseHelpFormatter
import co.uk.redpixel.vendingmachine.console.DispenseOptions
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.SystemExitException
import java.io.StringWriter
import java.lang.Runtime.getRuntime

@Volatile
var keepRunning: Boolean = true

fun main(args: Array<String>) {
    println("Welcome to Vending Machine!\n")
    println("Press Ctrl+C to quit..")

    try {
        val parser = ArgParser(args, helpFormatter = DispenseHelpFormatter())

        DispenseOptions(parser).run {
            val machine = VendingMachine.on(inventory = craftInventory())

            do {
                print("\nEnter amount: ")
                val amount = readLine()!!.toIntOrNull()

                when (amount) {
                    null -> println("Please enter a valid number.")
                    else -> try {
                        val change = machine dispense amount

                        if (change.any()) {
                            val total = change.total()
                            println("The least required coins ${if (total > 1) "are" else "is"} $total.")
                            println(change)
                        }
                        else println("No coins are dispensed.")

                    } catch (exception: Exception) {
                        when (exception) {
                            is NotEnoughBalanceException,
                            is InsufficientCoinageException -> println(exception.message)
                            else -> throw exception
                        }
                    }
                }
            } while (keepRunning)
        }
    } catch (exception: SystemExitException) {
        println(StringWriter().apply {
            exception.printUserMessage(this, "vending-machine", 100)
        })
    } catch (exception: Exception) {
        println("Unhandled error occurred: ${exception.message}")
    }

    getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
            keepRunning = false
        }
    })
}
