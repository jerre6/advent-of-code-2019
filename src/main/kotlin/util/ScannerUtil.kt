package util

import java.util.*

fun Scanner.readLines(): List<String> {
    val lines = mutableListOf<String>()
    while (this.hasNextLine()) {
        lines.add(this.nextLine().trim())
    }
    return lines
}

fun Scanner.readIntProgram(): List<Long> {
    return this.nextLine().trim().split(",").map { it.toLong() }
}