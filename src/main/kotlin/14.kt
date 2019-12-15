import util.readLines
import java.io.File
import java.util.*
import kotlin.math.ceil

typealias ChemicalItem = Pair<Long, String>

fun main() {
    val sc = Scanner(File("input/14.txt"))
    val map = sc.readLines().map { parseLine(it) }.toMap()
    println("14a: ${solve14a(map)}")
    println("14b: ${solve14b(map)}")
}

fun solve14a(reqMap: Map<ChemicalItem, List<ChemicalItem>>): Long {
    return solve14(1, reqMap)
}

fun solve14b(reqMap: Map<ChemicalItem, List<ChemicalItem>>): Long {
    val target = 1000000000000L
    var low = 0L
    var high = 1000000000000L
    while (low <= high) {
        val mid = (low + high) / 2
        val ore = solve14(mid, reqMap)
        when {
            ore < target -> low = mid + 1
            ore > target -> high = mid - 1
            else -> return mid
        }
    }
    return high
}


fun solve14(amount: Long, reqMap: Map<ChemicalItem, List<ChemicalItem>>): Long {
    val requirements = mutableMapOf("FUEL" to amount)
    while (requirements.any { it.key != "ORE" && it.value > 0 }) {
        val entry = requirements.entries.first { it.key != "ORE" && it.value > 0 }
        val (result, reqs) = reqMap.entries.first { it.key.second == entry.key }
        val resultAmount = ceil(entry.value.toDouble() / result.first).toInt()
        requirements.compute(result.second) { _, oldValue -> (oldValue ?: 0) - resultAmount * result.first }
        reqs.forEach { r -> requirements.compute(r.second) { _, oldValue -> (oldValue ?: 0) + (r.first * resultAmount) } }
    }
    return requirements["ORE"] ?: -1L
}

fun parseLine(line: String): Pair<ChemicalItem, List<ChemicalItem>> {
    val (first, second) = line.split("=>")
    val reqs = mutableListOf<ChemicalItem>()
    for (req in first.split(",")) {
        val (amount, name) = req.trim().split(" ")
        reqs.add(Pair(amount.toLong(), name))
    }
    val (resultAmount, resultName) = second.trim().split(" ")
    return Pair(Pair(resultAmount.toLong(), resultName), reqs)
}