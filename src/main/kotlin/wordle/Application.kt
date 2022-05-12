package wordle

import wordle.domain.Answer
import wordle.domain.Color
import wordle.domain.Color.GREEN
import wordle.domain.Word
import wordle.domain.WordPicker
import wordle.view.InputView
import wordle.view.OutputView

fun main() {
    val wordPicker = WordPicker()
    val answer = Answer(wordPicker.pickTodayAnswer())
    OutputView.printIntroduction()

    if (playWordle(answer)) {
        return
    }
    OutputView.printAnswer(answer)
}

private fun playWordle(answer: Answer): Boolean {
    repeat(6) {
        val guessWord = guessAnswer()
        val result = answer.compare(guessWord)
        OutputView.printResult(result)
        if (isAllGreen(result)) {
            OutputView.printCount(it + 1)
            return true
        }
    }
    return false
}

private fun isAllGreen(result: List<Color>): Boolean {
    return result.all { it == GREEN }
}


fun guessAnswer(): Word {
    return try {
        Word(InputView.inputGuess())
    } catch (e: IllegalArgumentException) {
        OutputView.printError(e.message)
        guessAnswer()
    }
}
