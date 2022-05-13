package wordle.controller

import wordle.domain.Answer
import wordle.domain.Game
import wordle.domain.Words
import wordle.view.printInputMessage
import wordle.view.printResults
import wordle.view.printStartMessage
import java.time.LocalDate

class WordleController {

    fun run() {
        printStartMessage()
        val game = Game(Words.pick(LocalDate.now()))
        while (game.isPlaying) {
            val answer = Answer(printInputMessage())
            game.playRound(answer)
            printResults(game.results, game.isPlaying, game.findTryCount())
        }
    }
}
