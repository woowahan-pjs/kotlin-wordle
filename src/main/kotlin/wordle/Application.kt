package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game()
    OutputView.printIntroduction(Game.maxCount)
    while (!game.isOver()) {
        val word = inputGuessWord()
        val results = game.playWordle(word)
        OutputView.printResult(results)
    }
    OutputView.printCount(game.count)
}

fun inputGuessWord(): Word {
    return try {
        Word(InputView.inputGuess())
    } catch (e: IllegalArgumentException) {
        OutputView.printError(e.message)
        return inputGuessWord()
    }
}
