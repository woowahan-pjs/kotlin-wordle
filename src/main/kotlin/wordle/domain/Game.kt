package wordle.domain

class Game(tryCount: TryCount, private val answer: Word) {
    private var _tryCount: TryCount = tryCount

    fun play(input: Word): List<Tile> {
        val inputChars = input.value.toCharArray()
        val wordMatcher = WordMatcher(answer)

        val resultTiles = createResultTiles(inputChars, wordMatcher)

        if (isWinner(resultTiles)) {
            return resultTiles;
        }

        _tryCount = _tryCount.increase()

        return resultTiles
    }

    fun isWinner(resultTiles: List<Tile>): Boolean {
        return resultTiles.containsAll(
            listOf(Tile.GREEN, Tile.GREEN, Tile.GREEN, Tile.GREEN, Tile.GREEN)
        )
    }

    fun tryCount(): TryCount {
        return _tryCount
    }

    private fun createResultTiles(inputChars: CharArray, wordMatcher: WordMatcher): List<Tile> {
        val resultTiles = mutableListOf<Tile>()

        inputChars.forEachIndexed { index, it ->
            resultTiles.add(wordMatcher.match(it.toString(), index))
        }

        return resultTiles.toList()
    }

    fun retrieveResultTiles(): List<Tile> {
        return listOf(Tile.YELLOW, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY)
    }
}
