import shared.IntProgram
import util.readIntProgram
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/7.txt"))
    val program = sc.readIntProgram()
    println("7a: ${solve7a(program.toMutableList())}")
    println("7b: ${solve7b(program.toMutableList())}")
}

fun solve7a(memory: List<Long>): Long {
    val permutations = permute(List(5) { it.toLong() })
    var maxOutput = 0L
    for (permutation in permutations) {
        var currentInput = 0L
        for (i in 0..4) {
            val program = IntProgram(memory)
            currentInput = program.run(listOf(permutation[i], currentInput)).first()
        }
        maxOutput = maxOf(maxOutput, currentInput)
    }
    return maxOutput
}

fun solve7b(memory: List<Long>): Long {
    val permutations = permute(List(5) { it + 5L })
    var maxOutput = 0L
    for (permutation in permutations) {
        val machines = Array(5) { IntProgram(memory) }
        val lastMachine = machines.last()
        var currentInput = listOf(0L)
        for (i in machines.indices) {
            val machine = machines[i]
            currentInput = machine.run(listOf(permutation[i]).plus(currentInput))
        }
        while (!lastMachine.isFinished()) {
            for (machine in machines) {
                currentInput = machine.run(currentInput)
            }
        }
        maxOutput = maxOf(maxOutput, currentInput.last())
    }
    return maxOutput
}


// source: https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}