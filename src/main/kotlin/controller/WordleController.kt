package controller

import domain.Game
import domain.Input
import domain.MatchResults
import domain.Output
import infra.WordsPool

class WordleController(
    private val input: Input,
    private val output: Output
) {

    fun start() {
        val game = Game(WordsPool())
        do {
            var result: MatchResults

            try {
                result = game.progress(input)
            } catch (e: IllegalArgumentException) {
                println(e.message)
                continue
            }
            output.write(result)

            if (result.isCorrect()) {
                break
            }
        } while (!game.checkToRetry())
    }
}
