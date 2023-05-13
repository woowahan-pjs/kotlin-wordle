import view.InputView
import view.OutputView
import wordle.domain.WordPicker
import wordle.domain.WordleGame

class WordleController {

    fun run() {
        val wordleGame = WordleGame(WordPicker())

        OutputView.printGameStart()

        do {
            val userGuess = InputView.readUserGuess()
            val gameHistory = wordleGame.playGame(userGuess)
            OutputView.printResult(gameHistory)
        } while (!wordleGame.isOver())
    }
}
