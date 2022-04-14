package wordle.controller

import wordle.domain.Answers
import wordle.domain.Game
import wordle.domain.Tiles
import wordle.domain.Word
import wordle.view.InputView
import wordle.view.ResultView
import java.io.File

private const val START_PLAY_COUNT = 1
private const val LAST_PLAY_COUNT = 7

fun main() {
    val game = Game(Answers.ANSWER)

    ResultView.printInit()

    val results = mutableListOf<Tiles>()
    playGame(game, results)
}

private fun playGame(game: Game, results: MutableList<Tiles>) {
    (START_PLAY_COUNT until LAST_PLAY_COUNT).forEachIndexed { _, index ->
        val inputWord = InputView.askWord(Answers.WORDS)

        val resultTiles = game.play(Word(inputWord))

        results.add(resultTiles)

        ResultView.printAllResults(results)

        checkIsWinner(game, resultTiles, index)
    }
}

private fun checkIsWinner(game: Game, resultTiles: Tiles, index: Int) {
    if (game.isWinner(resultTiles)) {
        ResultView.printGamePlayCount(index)
        throw IllegalStateException("게임이 종료되었습니다.")
    }
}

fun getResourceText(path: String): File {
    return File(ClassLoader.getSystemResource(path).file)
}
