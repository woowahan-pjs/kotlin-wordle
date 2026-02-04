package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.util.WordsReader
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game(WordsReader.getWords())
    OutputView.printStartMessage(game.maxGameCount)

    while (!game.isGameOver) {
        play(game)
        OutputView.printResults(game)
    }
}

private fun play(game: Game) {
    return try {
        val answer = Word(InputView.inputAnswer())
        game.match(answer)
    } catch (e: IllegalArgumentException) {
        OutputView.printErrorMessage(e.message.orEmpty())
        play(game)
    }
}
