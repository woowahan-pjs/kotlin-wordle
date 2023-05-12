package view

import wordle.domain.UserGuess

object InputView {

    fun readUserGuess(): UserGuess {
        OutputView.printRequestAnswer()
        return runCatching {
            UserGuess(readln())
        }.onFailure { OutputView.printError() }
            .getOrElse { readUserGuess() }
    }
}


