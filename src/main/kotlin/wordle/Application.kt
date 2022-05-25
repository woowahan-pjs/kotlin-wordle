package wordle

import wordle.domain.Game
import wordle.domain.GameResult
import wordle.domain.Word
import wordle.domain.Words
import wordle.domain.WordsReader
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

fun main() {

    val wordsReader = WordsReader("words.txt")
    val words = Words(wordsReader.words)
    val game = Game(words, LocalDate.now())
    val gameResult = GameResult()

    OutputView.printInitMessage(Game.MAX_ROUND)
    play(game, gameResult)
}

private fun play(game: Game, gameResult: GameResult) {
    return try {
        while (!game.isOver) {
            val playerWord = Word(InputView.requestAnswer())
            val matchResult = game.matchResult(playerWord)
            gameResult.add(matchResult)
            OutputView.printGameResult(game.isOver, game.count, Game.MAX_ROUND, gameResult)
        }
    } catch (exception: IllegalArgumentException) {
        OutputView.printErrorMessage(exception.message)
        play(game, gameResult)
    }
}
