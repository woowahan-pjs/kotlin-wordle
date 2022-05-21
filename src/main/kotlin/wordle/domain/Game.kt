package wordle.domain

import java.time.LocalDate

class Game(today: LocalDate) {

    var count = 0
        private set

    private val answer: Answer = Answer(WordPicker().pickAnswer(today))

    private val _guessResults: MutableList<Colors> = mutableListOf()
    val guessResults: List<Colors>
        get() = _guessResults

    fun playWordle(word: Word) {
        if (!isOver()) {
            _guessResults.add(Colors(answer.compare(word)))
            count++
        }
    }

    fun isOver(): Boolean {
        return count >= maxCount || _guessResults.any { it.isCorrect() }
    }

    companion object {
        const val maxCount = 6
    }
}
