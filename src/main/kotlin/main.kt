import domain.RetryableGame
import domain.Start
import domain.WordleGame
import view.InputView
import view.ResultView

fun main(args: Array<String>) {
    val resultView = ResultView()
    val inputView = InputView()
    val wordleGame = WordleGame()
    val game = RetryableGame(wordleGame, resultView, inputView)
    val start = Start()
    val answer = start.findAnswer()
    game.run(answer);
}
