package wordle.domain

class Game {

    var count = 0
        private set

    val answer: Answer = Answer(WordPicker().pickTodayAnswer())

    private val _guessResults: MutableList<Colors> = mutableListOf()

    fun playWordle(word: Word): List<Colors> {
        if (!isOver()) {
            _guessResults.add(Colors(answer.compare(word)))
            ++count
        }
        return _guessResults
    }

    fun isOver(): Boolean {
        return count >= maxCount || _guessResults.any { it.isCorrect() }
    }

    companion object {
        const val maxCount = 6
    }
}
