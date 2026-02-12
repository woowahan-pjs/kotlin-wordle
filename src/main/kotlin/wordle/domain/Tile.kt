package wordle.domain

enum class Tile(val symbol: String) {

    GRAY("⬜"),
    GREEN("\uD83D\uDFE9"),
    YELLOW("\uD83D\uDFE8");

    fun isGreen(): Boolean {
        return this == GREEN
    }
}
