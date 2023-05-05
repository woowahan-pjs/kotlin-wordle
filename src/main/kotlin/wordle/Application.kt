package wordle

import wordle.controller.WordleController
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    WordleController(InputView(), OutputView()).run()
}
