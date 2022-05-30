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
        val answer = Word(InputView.inputAnswer())
        game.match(answer)
        OutputView.printResults(game)
    }
}
