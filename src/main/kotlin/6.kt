import util.readLines
import java.io.File
import java.util.*

fun main() {
    val sc = Scanner(File("input/6.txt"))
    val orbitMap = sc.readLines()
        .map { it.split(")") }
        .map { it[1] to it[0] }
        .toMap()

    println("6a: ${orbitMap.keys.map { pathToRoot(it, orbitMap).size - 1 }.sum()}")
    val youToRootPath = pathToRoot("YOU", orbitMap).reversed()
    val sanToRootPath = pathToRoot("SAN", orbitMap).reversed()
    val firstDifferentIndex = youToRootPath.zip(sanToRootPath).indexOfFirst { it.first != it.second }
    println("6b: ${(youToRootPath.size - firstDifferentIndex - 1) + (sanToRootPath.size - firstDifferentIndex - 1)}")
}

fun pathToRoot(item: String, orbitMap: Map<String, String>): List<String> {
    val path = mutableListOf<String>()
    var current: String? = item
    while (current != null) {
        path.add(current)
        current = orbitMap[current]
    }
    return path
}