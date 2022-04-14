package wordle.domain

class Game(private val answer: Word) {

    fun play(input: Word): Tiles {
        val inputChars = input.value.toCharArray()
        val wordMatcher = WordMatcher(answer)

        val resultTiles = createResultTiles(inputChars, wordMatcher)

        if (resultTiles.isWinner()) {
            return resultTiles
        }

        return resultTiles
    }

    fun isWinner(resultTiles: Tiles): Boolean {
        return resultTiles.isWinner()
    }

    private fun createResultTiles(inputChars: CharArray, wordMatcher: WordMatcher): Tiles {
        val resultTiles = mutableListOf<Tile>()

        inputChars.forEachIndexed { index, it ->
            resultTiles.add(wordMatcher.match(it.toString(), index))
        }

        return Tiles(resultTiles.toList())
    }

    fun retrieveResultTiles(): Tiles {
        return Tiles(listOf(Tile.YELLOW, Tile.GRAY, Tile.GRAY, Tile.GRAY, Tile.GRAY))
    }
}
