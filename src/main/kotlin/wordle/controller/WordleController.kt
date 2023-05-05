package wordle.controller

import wordle.domain.Word
import wordle.domain.WordleComparator
import wordle.domain.WordleGame
import wordle.domain.WordleWordsFactory
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

class WordleController(
    private val inputView: InputView,
    private val outputView: OutputView
) {

    fun run() {
        outputView.printStartMessage()
        val words = WordleWordsFactory().generate()
        val wordleGame = WordleGame(words)
        val todaysWord = wordleGame.getTodaysWord(LocalDate.now())
        val wordleComparator = WordleComparator()

        // 검증 필요
        play(wordleGame, todaysWord)
    }

    private fun play(wordleGame: WordleGame, todaysWord: Word) {
        try {
            val rawWord = inputView.readGuessWord()
            wordleGame.play(todaysWord, rawWord, outputView::printResults)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            play(wordleGame, todaysWord)
        }
    }
}
