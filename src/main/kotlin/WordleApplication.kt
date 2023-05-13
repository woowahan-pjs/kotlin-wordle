import controller.WordleController
import view.InputView
import view.OutputView

fun main() {
    val wordleController = WordleController(InputView(), OutputView())
    wordleController.play()
}
