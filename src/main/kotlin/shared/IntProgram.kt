package shared

class IntProgram(originalMemory: List<Long>) {
    private val memory = HashMap<Long, Long>(originalMemory.mapIndexed { index, l -> index.toLong() to l }.toMap())
    private var finished = false
    private var base = 0L
    var current = 0L

    fun dumpMemory(): List<Long> {
        return memory.keys.sorted().map { memory[it]!! }
    }

    fun isFinished(): Boolean {
        return finished
    }

    fun run(input: List<Long>): List<Long> {
        if (finished) {
            return emptyList()
        }
        val output = mutableListOf<Long>()

        var inputIndex = 0
        while (true) {
            val fullOp = memory[current].toString().padStart(5, '0')
            val op = fullOp.slice(fullOp.length - 2 until fullOp.length).toInt()
            val modes = fullOp.slice(0 until fullOp.length - 2).toCharArray().map { it.toString().toLong() }.reversed()
            if (op == 1) {
                val (first, second, third) = readParams(3, modes)
                memory[third] = lookup(first) + lookup(second)
                current += 4
            } else if (op == 2) {
                val (first, second, third) = readParams(3, modes)
                memory[third] = lookup(first) * lookup(second)
                current += 4
            } else if (op == 3) {
                if (inputIndex >= input.size) {
                    break
                }
                val (first) = readParams(1, modes)
                memory[first] = input[inputIndex++]
                current += 2
            } else if (op == 4) {
                val (first) = readParams(1, modes)
                output.add(lookup(first))
                current += 2
            } else if (op == 5) {
                val (first, second) = readParams(2, modes)
                if (lookup(first) != 0L) {
                    current = lookup(second)
                } else {
                    current += 3
                }
            } else if (op == 6) {
                val (first, second) = readParams(2, modes)
                if (lookup(first) == 0L) {
                    current = lookup(second)
                } else {
                    current += 3
                }
            } else if (op == 7) {
                val (first, second, third) = readParams(3, modes)
                memory[third] = if (lookup(first) < lookup(second)) 1L else 0L
                current += 4
            } else if (op == 8) {
                val (first, second, third) = readParams(3, modes)
                memory[third] = if (lookup(first) == lookup(second)) 1L else 0L
                current += 4
            } else if (op == 9) {
                val (first) = readParams(1, modes)
                base += lookup(first)
                current += 2
            } else if (op == 99) {
                finished = true
                break
            }
        }
        return output
    }

    private fun lookup(location: Long): Long = memory[location] ?: 0L

    private fun readParams(amount: Int, modes: List<Long>): List<Long> {
        return List(amount) {
            when (modes[it]) {
                0L -> lookup(current + it + 1)
                1L -> current + it + 1
                2L -> base + lookup(current + it + 1)
                else -> throw Error("unreachable")
            }
        }
    }
}

