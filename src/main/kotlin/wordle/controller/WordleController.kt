package wordle.controller

import wordle.domain.Answer
import wordle.domain.Game
import wordle.domain.Words
import wordle.view.printErrorMessage
import wordle.view.printResults
import wordle.view.printStartMessage
import wordle.view.requestInput
import java.time.LocalDate

class WordleController {

    fun run() {
        printStartMessage()
        val game = Game(Words.pick(LocalDate.now()))

        while (game.isPlaying) {
            val answer = requestAnswer()
            game.playRound(answer)
            printResults(game.results, game.isPlaying, game.findTryCount())
        }
    }

    private fun requestAnswer(): Answer {
        return try {
            Answer(requestInput())
        } catch (e: IllegalArgumentException) {
            printErrorMessage(e.message)
            requestAnswer()
        }
    }
}
