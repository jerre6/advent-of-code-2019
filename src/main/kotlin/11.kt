import shared.Coordinate
import shared.Direction
import shared.IntProgram
import java.io.File
import java.util.*

class Robot(
    private var row: Int,
    private var col: Int,
    private var direction: Direction
) {
    fun moveForward() {
        row = when (direction) {
            Direction.U -> row - 1
            Direction.D -> row + 1
            else -> row
        }
        col = when (direction) {
            Direction.L -> col - 1
            Direction.R -> col + 1
            else -> col
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            Direction.D -> Direction.R
            Direction.L -> Direction.D
            Direction.R -> Direction.U
            Direction.U -> Direction.L
        }
    }

    fun turnRight() {
        direction = when (direction) {
            Direction.D -> Direction.L
            Direction.L -> Direction.U
            Direction.R -> Direction.D
            Direction.U -> Direction.R
        }
    }

    fun getLocation(): Coordinate {
        return Coordinate(row, col)
    }
}

fun main() {
    val sc = Scanner(File("input/11.txt"))
    val memory = sc.nextLine().split(",").map { it.toLong() }
    println("11a: ${solve11a(memory)}")
    println("11b output")
    solve11b(memory)
}

fun solve11a(memory: List<Long>): Int {
    val program = IntProgram(memory)
    val robot = Robot(0, 0, Direction.U)
    val board = mutableMapOf<Coordinate, Long>()
    val locations = mutableSetOf<Coordinate>()
    while (!program.isFinished()) {
        val (color, direction) = program.run(listOf(board[robot.getLocation()] ?: 0))
        board[robot.getLocation()] = color
        locations.add(robot.getLocation())
        if (direction == 0L) {
            robot.turnLeft()
        } else {
            robot.turnRight()
        }
        robot.moveForward()
    }
    return locations.size
}

fun solve11b(memory: List<Long>) {
    val program = IntProgram(memory)
    val robot = Robot(0, 0, Direction.U)
    val board = mutableMapOf<Coordinate, Long>()
    board[robot.getLocation()] = 1L
    while (!program.isFinished()) {
        val (color, direction) = program.run(listOf(board[robot.getLocation()] ?: 0))
        board[robot.getLocation()] = color
        if (direction == 0L) {
            robot.turnLeft()
        } else {
            robot.turnRight()
        }
        robot.moveForward()
    }

    val minRow = 0
    val maxRow = 10
    val minCol = 0
    val maxCol = 100

    val result = List(maxRow - minRow) {r -> List(maxCol - minCol) {c -> board[Coordinate(r - minRow, c - minCol)] ?: 0} }
    result.forEach {println(it.map { if (it == 0L) " " else "#" }.joinToString(""))}
}
