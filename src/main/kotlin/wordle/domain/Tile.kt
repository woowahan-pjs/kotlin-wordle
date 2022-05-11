package wordle.domain

enum class Tile(val symbol: String) {
    GREEN("\uD83D\uDFE9"),
    YELLOW("\uD83D\uDFE8"),
    GRAY("⬜");

    fun isGreen() : Boolean {
        return this == GREEN
    }
}