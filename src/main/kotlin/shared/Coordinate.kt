package shared

data class Coordinate(
    val x: Int,
    val y: Int
) {
    fun move(direction: Direction): Coordinate {
        return when (direction) {
            Direction.D -> Coordinate(x, y - 1)
            Direction.U -> Coordinate(x, y + 1)
            Direction.L -> Coordinate(x - 1, y)
            Direction.R -> Coordinate(x + 1, y)
        }
    }
}
