package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.util.WordsReader
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game(WordsReader.getWords())
    var isGameOver = false
    OutputView.printStartMessage(game.maxGameCount)

    while (!isGameOver) {
        val answer = Word(InputView.inputAnswer())
        game.match(answer)
        isGameOver = game.isGameOver(answer)
        OutputView.printResults(game, isGameOver)
    }
}
