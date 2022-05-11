package wordle.domain

enum class Tile {
    GREEN,
    YELLOW,
    GRAY;

    fun isGreen() : Boolean {
        return this == GREEN
    }
}