import shared.IntProgram
import util.readIntProgram
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/2.txt"))
    val memory = sc.readIntProgram()
    println("2a: ${solve2a(memory)}")
    println("2b: ${solve2b(memory)}")
}

fun solve2a(originalMemory: List<Long>): Long {
    val memory = originalMemory.toMutableList()
    memory[1] = 12
    memory[2] = 2
    val program = IntProgram(memory)
    program.run(emptyList())
    return program.dumpMemory()[0]
}

fun solve2b(originalMemory: List<Long>): Long {
    for (noun in 0L..99L) {
        for (verb in 0L..99L) {
            val memory = originalMemory.toMutableList()
            memory[1] = noun
            memory[2] = verb
            val program = IntProgram(memory)
            program.run(emptyList())
            if (program.dumpMemory()[0] == 19690720L) {
                return 100 * noun + verb
            }
        }
    }
    throw Error("No solution found.")
}
