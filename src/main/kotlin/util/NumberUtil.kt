package util

fun Int.toDigits(): List<Int> {
    val digits = mutableListOf<Int>()
    var current = this
    while (current >= 10) {
        digits.add(current % 10)
        current /= 10
    }
    digits.add(current)
    return digits.reversed()
}