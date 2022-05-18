package wordle.controller

import wordle.domain.Answer
import wordle.domain.Game
import wordle.domain.Words
import wordle.view.*
import java.time.LocalDate

class WordleController {

    fun run() {
        printStartMessage()
        val game = Game(Words.pick(LocalDate.now()))
        while (game.isPlaying) {
            game.playRound(Answer(inputAnswer()))
            printResults(game)
        }
    }

    private fun printResults(game: Game) {
        if (!game.isPlaying) {
            printTryCount(game.findTryCount())
        }
        printResults(game.results)
    }
}
