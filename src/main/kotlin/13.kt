import shared.IntProgram
import util.readIntProgram
import java.io.File
import java.util.*

data class Tile(
    val col: Long,
    val row: Long
)

fun main() {
    val sc = Scanner(File("input/13.txt"))
    val program = sc.readIntProgram()
    println("13a: ${solve13a(program.toList())}")
    println("13b: ${solve13b(program.toList())}")
}

fun solve13a(memory: List<Long>): Int {
    val program = IntProgram(memory)
    val output = program.run(listOf())
    var count = 0
    for (i in output.indices step 3) {
        if (output[i + 2] == 2L) {
            count++
        }
    }
    return count
}

fun solve13b(originalMemory: List<Long>): Long {
    val memory = originalMemory.toMutableList()
    memory[0] = 2
    val program = IntProgram(memory)
    val paddle = mutableSetOf<Tile>()
    var input = listOf<Long>()
    var ball: Tile? = null
    var score = 0L


    while (!program.isFinished()) {
        println("input: $input")
        val output = program.run(input)
        println("output $output")
        for (i in output.indices step 3) {
            if (output[i] == -1L && output[i + 1] == 0L) {
                score = output[i + 2]
                println(output[i + 2])
            } else if (output[i + 2] == 0L) {
                paddle.remove(Tile(output[i], output[i + 1]))
            } else if (output[i + 2] == 4L) {
                ball = Tile(output[i], output[i + 1])
            } else if (output[i + 2] == 3L) {
                paddle.add(Tile(output[i], output[i + 1]))
            }
        }
        val minCol = paddle.minBy { it.col }?.col ?: -1L
        val maxCol = paddle.maxBy { it.col }?.col ?: Long.MAX_VALUE
        input = listOf(0)
        if (ball != null) {
            if (ball.col < minCol) {
                input = listOf(-1)
            }
            if (ball.col > maxCol) {
                input = listOf(+1)
            }
        }
    }
    return score
}