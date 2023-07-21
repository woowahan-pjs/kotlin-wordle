package edu.nextstep.wordle.application.wordle

import edu.nextstep.wordle.application.wordle.window.Alphabet
import edu.nextstep.wordle.application.wordle.window.AlphabetFactory
import edu.nextstep.wordle.application.wordle.window.Match
import edu.nextstep.wordle.application.wordle.window.Window
import edu.nextstep.wordle.application.wordle.window.WindowResult

data class Word(
    val windows: Set<Window>,
) {
    init {
        if (this.windows.size != WORD_SIZE) {
            throw IllegalArgumentException("${this.windows.size}: 단어의 사이즈는 ${WORD_SIZE}여야 합니다.")
        }
    }

    fun match(input: Word): List<WindowResult> {
        var results = listOf<WindowResult>()

        for (inputWindow in input.windows) {
            val windowResult = getWindowResult(inputWindow)
            results = results + windowResult
        }

        return results
    }

    private fun getWindowResult(inputWindow: Window): WindowResult {
        var windowResult = WindowResult(position = inputWindow.position, match = Match.MISS)

        for (targetWindow in this.windows) {
            val match = targetWindow.match(inputWindow)
            windowResult = windowResult.update(match)
        }

        return windowResult
    }

    companion object {
        private const val WORD_SIZE: Int = 5

        fun create(input: String): Word {
            val alphabetFactory = AlphabetFactory.instance
            val windows = input.mapIndexed { index, alphabet ->
                Window(alphabet = alphabetFactory.findBy(alphabet.toString()), position = index)
            }.toSet()
            return Word(windows)
        }
    }
}
