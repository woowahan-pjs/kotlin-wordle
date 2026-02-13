package edu.nextstep.wordle.config

import edu.nextstep.wordle.application.wordle.Word
import edu.nextstep.wordle.application.wordle.WordResults
import edu.nextstep.wordle.application.wordle.Wordle
import edu.nextstep.wordle.application.wordle.WordleAnswer
import edu.nextstep.wordle.application.wordle.dictionary.WordFinder
import edu.nextstep.wordle.application.wordle.dictionary.WordProvider
import edu.nextstep.wordle.presentation.inputWord
import edu.nextstep.wordle.presentation.showAnswer
import edu.nextstep.wordle.presentation.showTiles
import edu.nextstep.wordle.presentation.start

class WordleGame(
    private val wordProvider: WordProvider,
    private val wordFinder: WordFinder,
) {
    fun run() {
        val targetWord = wordProvider.provide()
        var wordle = Wordle(target = targetWord, wordFinder = wordFinder)
        start()

        while (wordle.untilEnd()) {
            val input: Word = inputWord()
            val answer: WordleAnswer = wordle.input(input)
            wordle = doAnswer(answer)
            showTiles(wordle.wordResult)
        }

        showAnswer(WordResults(wordle.wordResult))
    }

    private fun Wordle.untilEnd() = !this.isEnd()

    private fun doAnswer(answer: WordleAnswer): Wordle {
        when (answer) {
            is WordleAnswer.Retry -> println(answer.message)
            is WordleAnswer.Right -> Unit
        }
        return answer.wordle
    }
}
