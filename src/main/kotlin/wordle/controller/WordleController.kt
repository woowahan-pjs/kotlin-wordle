package wordle.controller

import wordle.domain.Word
import wordle.domain.WordleGame
import wordle.domain.WordleWords
import wordle.domain.WordleWordsFactory
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

class WordleController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        outputView.printStartMessage()
        val words = WordleWordsFactory().generate()
        val answerWord = words.getTodaysWord(LocalDate.now())
        val wordleGame = WordleGame(answerWord)

        while (wordleGame.isNotEnd()) {
            val word = getWord(words)
            play(word, wordleGame)
        }
    }

    private fun play(word: Word, wordleGame: WordleGame) {
        val results = wordleGame.play(word)

        if (wordleGame.isCorrect) {
            outputView.printCount(WordleGame.MAX_ROUND, wordleGame.count)
        }
        outputView.printResults(results)
    }

    private fun getWord(words: WordleWords): Word {
        while (true) {
            runCatching {
                val word = inputView.readGuessWord()
                if (words.contains(word)) return word
            }.getOrElse {
                println(it.message)
            }
        }
    }
}
