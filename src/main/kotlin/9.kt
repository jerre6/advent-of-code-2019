import shared.IntProgram
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/9.txt"))
    val program = sc.nextLine().split(",").map { it.toLong() }
    println("9a: ${solve9a(program.toMutableList())}")
    println("9b: ${solve9b(program.toMutableList())}")
}

fun solve9a(program: List<Long>): Long {
    return IntProgram(program).run(listOf(1)).first()
}

fun solve9b(program: List<Long>): Long {
    return IntProgram(program).run(listOf(2)).first()
}