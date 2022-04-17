package wordle.controller

import wordle.domain.Answer
import wordle.domain.Game
import wordle.domain.ResultTiles
import wordle.domain.Position
import wordle.domain.Word
import wordle.utils.WordsCreator
import wordle.view.InputView
import wordle.view.ResultView

private const val LAST_PLAY_COUNT = 6

fun main() {
    val game = Game(Answer(WordsCreator(), Position(Answer.ANSWER_POSITION)).answer)

    ResultView.printInit()

    playGame(game)
}

private fun playGame(game: Game) {
    val results = ResultTiles()

    var tryCount = 0

    while (tryCount < LAST_PLAY_COUNT) {
        val inputWord = InputView.askWord(WordsCreator.WORDS)
        val resultTiles = game.play(Word(inputWord))
        val combined = results.combine(resultTiles)
        ResultView.printAllResults(combined.resultTiles)

        tryCount++

        if (game.isWinner(resultTiles)) {
            ResultView.printGamePlayCount(tryCount)
            break
        }
    }
}
