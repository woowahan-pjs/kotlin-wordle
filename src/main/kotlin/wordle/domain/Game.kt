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

    private fun createResultTiles(inputChars: CharArray, wordMatcher: WordMatcher): Tiles {
        val resultTiles = mutableListOf<Tile>()

        inputChars.forEachIndexed { index, it ->
            resultTiles.add(wordMatcher.match(it.toString(), index))
        }

        return Tiles(resultTiles)
    }

    fun isWinner(resultTiles: Tiles): Boolean {
        return resultTiles.isWinner()
    }
}
