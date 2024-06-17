package wordle.domain

class Game(private val answer: Word) {

    fun play(input: Word): Tiles {
        val inputChars = input.value.toCharArray()
        val wordMatcher = WordMatcher(answer)

        return createResultTiles(inputChars, wordMatcher)
    }

    private fun createResultTiles(inputChars: CharArray, wordMatcher: WordMatcher): Tiles {
        return Tiles(inputChars.mapIndexed { index, it -> wordMatcher.match(it.toString(), index) })
    }

    fun isWinner(resultTiles: Tiles): Boolean {
        return resultTiles.isWinner()
    }
}
