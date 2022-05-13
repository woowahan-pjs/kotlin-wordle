package wordle

fun main() {
    val game = Game(WordsReader.getWords())
    OutputView.printStartMessage()
    doGame(game)
    OutputView.printCount(game.count)
    OutputView.printResults(game.results)
}

private fun doGame(game: Game) {
    val answer = Word(InputView.inputAnswer())
    game.match(answer)
    if (game.isGameOver(answer)) {
        return
    }
    OutputView.printResults(game.results)
    doGame(game)
}
