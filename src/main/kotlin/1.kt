import util.readLines
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/1.txt"))
    val input = sc.readLines().map { it.toInt() }
    println("1a: ${input.map { calculateFuel(it) }.sum()}")
    println("1b: ${input.map { calculateFuelRecursively(it) }.sum()}")
}

fun calculateFuel(mass: Int) = mass / 3 - 2

fun calculateFuelRecursively(mass: Int): Int {
    val currentFuel = calculateFuel(mass)
    return when {
        currentFuel <= 0 -> 0
        else -> currentFuel + calculateFuelRecursively(currentFuel)
    }
}