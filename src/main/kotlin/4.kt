import util.toDigits

fun main() {
    val from = 235741
    val to = 706948
    println("4a: ${solve4a(from, to)}")
    println("4b: ${solve4b(from, to)}")
}

fun solve4a(from: Int, to: Int): Int {
    return (from..to)
        .map { pass -> pass.toDigits() }
        .filter { pass -> (0..pass.size - 2).all { pass[it] <= pass[it + 1] } }
        .filter { pass -> (0..9).any { digit -> pass.count { it == digit } >= 2 } }
        .count()
}

fun solve4b(from: Int, to: Int): Int {
    return (from..to)
        .map { pass -> pass.toDigits() }
        .filter { pass -> (0..pass.size - 2).all { pass[it] <= pass[it + 1] } }
        .filter { pass -> (0..9).any { digit -> pass.count { it == digit } == 2 } }
        .count()
}