package wordle.controller

import wordle.application.WordleGame
import wordle.view.printStartingGameMessage
import java.time.LocalDate

class WordleGameController {
    private val gameStartDate: LocalDate = LocalDate.now()
    private val wordleGame: WordleGame = WordleGame(gameStartDate)

    fun run() {
        printStartingGameMessage()
        wordleGame.play()
    }
}
