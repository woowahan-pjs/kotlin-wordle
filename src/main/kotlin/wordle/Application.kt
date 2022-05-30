package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.utils.WordsReader
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game(WordsReader.getWords())
    OutputView.printStartMessage(game)
    var isOver = false

    while (!isOver) {
        val answer = Word(InputView.inputAnswer())
        game.match(answer)
        isOver = game.isGameOver(answer)
        OutputView.printResults(game, isOver)
    }
}
