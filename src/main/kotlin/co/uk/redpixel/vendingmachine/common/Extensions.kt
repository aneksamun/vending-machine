package co.uk.redpixel.vendingmachine.common

fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray> =
        Array(sizeOuter) { IntArray(sizeInner) }

fun Array<IntArray>.init(outer: Int, item: (Int) -> Int) : Array<IntArray> {
    for (inner in 0 until this[outer].size) {
        this[outer][inner] = item(inner)
    }
    return this
}
