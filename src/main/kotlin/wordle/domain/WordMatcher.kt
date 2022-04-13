package wordle.domain

class WordMatcher(private val answer: Word) {

    fun match(alphabet: String, index: Int): Tile {
        if (alphabet == answer.foundAlphabet(index)) {
            return Tile.GREEN
        }

        if (answer.contains(alphabet)) {
            return Tile.YELLOW
        }

        return Tile.GRAY
    }
}
