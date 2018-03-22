package co.uk.redpixel.vendingmachine.common

import co.uk.redpixel.vendingmachine.supply.Inventory

class NotEnoughBalanceException : Exception("Not enough balance to perform operation")
class InsufficientCoinageException : Exception("Not enough coins to dispense amount")
class InvalidCoinDenominationException(denomination: Int) : Exception("Could not match any coin by $denomination")
class UnsupportedInventory(inventory: Inventory<*>) : Exception("Could not dispense inventory of ${inventory.javaClass.simpleName}")
