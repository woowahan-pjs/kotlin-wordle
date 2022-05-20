package wordle

import wordle.domain.Game
import wordle.domain.Word
import wordle.util.WordsReader
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val game = Game(WordsReader.getWords())
    OutputView.printStartMessage()
    playGame(game)
    OutputView.printCount(game.count)
    OutputView.printResults(game.results)
}

private fun playGame(game: Game) {
    val answer = Word(InputView.inputAnswer())
    game.match(answer)
    if (game.isGameOver(answer)) {
        return
    }
    OutputView.printResults(game.results)
    playGame(game)
}
