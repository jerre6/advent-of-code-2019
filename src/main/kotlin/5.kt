import shared.IntProgram
import util.readIntProgram
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/5.txt"))
    val memory = sc.readIntProgram()
    println("5a: ${solve5a(memory)}")
    println("5b: ${solve5b(memory)}")
}

fun solve5a(memory: List<Long>): Long {
    val program = IntProgram(memory)
    return program.run(listOf(1)).last()
}

fun solve5b(memory: List<Long>): Long {
    val program = IntProgram(memory)
    return program.run(listOf(5)).last()
}
