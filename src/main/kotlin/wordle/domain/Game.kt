package wordle.domain

class Game(tryCount: TryCount, private val answer: Word) {
    private var _tryCount: TryCount = tryCount

    fun play(input: Word): Tiles {
        val inputChars = input.value.toCharArray()
        val wordMatcher = WordMatcher(answer)

        val resultTiles = createResultTiles(inputChars, wordMatcher)

        if (resultTiles.isWinner()) {
            return resultTiles
        }

        _tryCount = _tryCount.increase()

        return resultTiles
    }

    fun isWinner(resultTiles: Tiles): Boolean {
        return resultTiles.isWinner()
    }

    fun tryCount(): TryCount {
        return _tryCount
    }

    private fun createResultTiles(inputChars: CharArray, wordMatcher: WordMatcher): Tiles {
        val resultTiles = mutableListOf<Tile>()

        inputChars.forEachIndexed { index, it ->
            resultTiles.add(wordMatcher.match(it.toString(), index))
        }

        return Tiles(resultTiles.toList())
    }

    fun retrieveResultTiles(): List<Tile> {
        return listOf(Tile.YELLOW, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY)
    }
}
