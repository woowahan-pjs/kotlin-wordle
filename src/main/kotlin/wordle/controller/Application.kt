package wordle.controller

import wordle.domain.Answers
import wordle.domain.Game
import wordle.domain.Results
import wordle.domain.Word
import wordle.view.InputView
import wordle.view.ResultView

private const val LAST_PLAY_COUNT = 6

fun main() {
    val game = Game(Answers().answer)

    ResultView.printInit()

    playGame(game)
}

private fun playGame(game: Game) {
    val results = Results()

    var tryCount = 0

    while (tryCount < LAST_PLAY_COUNT) {
        val inputWord = InputView.askWord(Answers.WORDS)
        val resultTiles = game.play(Word(inputWord))
        results.combine(resultTiles)

        ResultView.printAllResults(results)

        tryCount++

        if (game.isWinner(resultTiles)) {
            ResultView.printGamePlayCount(tryCount)
            break
        }
    }
}
