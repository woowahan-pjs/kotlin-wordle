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

    OutputView.printInitMessage()
    play(game, gameResult)
}

private fun play(game: Game, gameResult: GameResult) {
    return try {
        while (!game.getIsOver) {
            val playerWord = Word(InputView.requestAnswer())
            val matchResult = game.matchResult(playerWord)
            gameResult.add(matchResult)
            OutputView.printGameResult(game.getIsOver, game.getCount, gameResult)
        }
    } catch (exception: IllegalArgumentException) {
        OutputView.printErrorMessage(exception.message)
        play(game, gameResult)
    }
}
