package wordle

import wordle.domain.*
import wordle.view.InputView
import wordle.view.OutputView
import java.time.LocalDate

fun main() {

    val wordsResponse = WordsResponse("words.txt")
    val words = Words(wordsResponse.words)
    val game = Game(words, LocalDate.now())
    val gameResult = GameResult()

    OutputView.printInitMessage(game.maxRound)
    play(game, gameResult)
}

private fun play(game: Game, gameResult: GameResult) {
    return try {
        while (!game.isOver) {
            val playerWord = Word(InputView.requestAnswer())
            val matchResult = game.matchResult(playerWord)
            gameResult.add(matchResult)
            OutputView.printGameResult(game.isOver, game.count, game.maxRound, gameResult)
        }
    } catch (exception: IllegalArgumentException) {
        OutputView.printErrorMessage(exception.message)
        play(game, gameResult)
    }
}
