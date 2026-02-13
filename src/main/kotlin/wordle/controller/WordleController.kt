package wordle.controller

import wordle.domain.Answer
import wordle.domain.Game
import wordle.domain.pickTodayWord
import wordle.view.inputAnswer
import wordle.view.printResults
import wordle.view.printStartMessage
import java.time.LocalDate

class WordleController {

    fun run() {
        printStartMessage()
        val game = Game(LocalDate.now().pickTodayWord())
        while (game.isPlaying) {
            game.playRound(Answer(inputAnswer()))
            printResults(game)
        }
    }
}
