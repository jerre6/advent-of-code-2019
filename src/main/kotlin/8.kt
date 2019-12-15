import java.io.File
import java.util.*

val rows = 6
val cols = 25
val fullSize = rows * cols

fun main() {
    val sc = Scanner(File("input/8.txt"))
    val input = sc.nextLine().toCharArray().map { it.toString().toInt() }
    println("8a: ${solve8a(input)}")
    println("8b:")
    solve8b(input)
}

fun solve8a(input: List<Int>): Int {
    val layers = List(input.size / fullSize) { l -> List(fullSize) { input[l * fullSize + it] } }
    val bestLayer = layers.minBy { it.count { it == 0 } } ?: emptyList()
    return bestLayer.count { it == 1 } * bestLayer.count { it == 2 }
}

fun solve8b(input: List<Int>) {
    val amountOfLayers = input.size / fullSize
    val layers =
        List(amountOfLayers) { l -> List(rows) { r -> List(cols) { c -> input[l * fullSize + r * cols + c] } } }
    val image = List(rows) { r -> List(cols) {c -> layers.first { it[r][c] != 2 }[r][c]} }
    image.map { println(it.map { if (it == 0) " " else "#" }.joinToString("")) }
}
