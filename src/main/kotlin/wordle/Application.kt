package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

fun main() {
    val game = Game(LocalDate.now())
    OutputView.printIntroduction(Game.maxCount)
    while (!game.isOver()) {
        val word = inputGuessWord()
        game.playWordle(word)
        OutputView.printResult(game.guessResults)
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
