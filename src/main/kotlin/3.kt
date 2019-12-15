import shared.Coordinate
import shared.Direction
import java.io.File
import java.util.*
import kotlin.math.absoluteValue

data class Instruction(
    val direction: Direction,
    val amount: Int
) {
    companion object {
        fun fromString(input: String): Instruction {
            return Instruction(
                Direction.valueOf(input.substring(0, 1)),
                Integer.parseInt(input.substring(1))
            )
        }
    }
}

fun main() {
    val sc = Scanner(File("input/3.txt"))
    val first = sc.nextLine().trim().split(",").map { Instruction.fromString(it) }
    val second = sc.nextLine().trim().split(",").map { Instruction.fromString(it) }
    val (firstPath, firstDist) = createPath(first)
    val (secondPath, secondDist) = createPath(second)
    val both = firstPath.intersect(secondPath)
    println("3a: ${both.map { it.x.absoluteValue + it.y.absoluteValue }.min()}")
    println("3b: ${both.map { firstDist.getValue(it) + secondDist.getValue(it) }.min()}")
}

fun createPath(instructions: List<Instruction>): Pair<List<Coordinate>, Map<Coordinate, Int>> {
    val distanceMap = mutableMapOf<Coordinate, Int>()
    val path = mutableListOf<Coordinate>()
    var current = Coordinate(0, 0)
    var dist = 0
    for (instruction in instructions) {
        for (i in 0 until instruction.amount) {
            dist++
            val newCoordinate = current.move(instruction.direction)
            path.add(newCoordinate)
            current = newCoordinate
            distanceMap[newCoordinate] = minOf(dist, distanceMap[newCoordinate] ?: Int.MAX_VALUE)
        }
    }
    return Pair(path, distanceMap)
}