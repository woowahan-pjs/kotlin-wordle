package study.operator

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point = Point(x + other.x, y + other.y)
    operator fun minus(other: Point): Point = Point(x - other.x, y - other.y)

    operator fun get(s: String): Int {
        return when (s) {
            "x" -> x
            "y" -> y
            else -> throw IllegalArgumentException()
        }
    }
}