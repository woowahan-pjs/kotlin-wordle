package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.utils.WordsReader
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game(WordsReader.getWords())
    OutputView.printStartMessage(game)
    doGame(game)
}

private fun doGame(game: Game) {
    try {
        submitAnswer(game)
    } catch (exception: IllegalArgumentException) {
        OutputView.printErrorMessage(exception)
        doGame(game)
    }
}

private fun submitAnswer(game: Game) {
    var isOver = false
    while (!isOver) {
        val answer = Word(InputView.inputAnswer())
        game.match(answer)
        isOver = game.isGameOver(answer)
        OutputView.printResults(game, isOver)
    }
}
