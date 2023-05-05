package wordle.domain

data class WordleGameResult(
    val result: List<TileColor>
) {
    fun isCorrect(): Boolean = result.all { color -> color == TileColor.GREEN }
}
