package controller

import domain.Hint
import domain.Question
import domain.TodayWordDictionary
import domain.TryCount
import view.InputView
import view.OutputView

class WordleController(val inputView: InputView, val outputView: OutputView) {

    private val wordDictionary = TodayWordDictionary()
    private val question: Question = Question(wordDictionary)
    private val maxTryCount: TryCount = TryCount(MAX_TRY_COUNT)
    private var tryCount: TryCount = TryCount()

    fun play() {
        val result: MutableList<List<Hint>> = mutableListOf()
        outputView.printWelcomeMessage()

        while (isGameNotEnd()) {
            val userAnswer: String = readAnswer()
            val hint: List<Hint> = question.getHint(userAnswer)

            result.add(hint)
            tryCount = tryCount.plus()

            if (question.isAnswer(userAnswer)) {
                outputView.printTryCount(tryCount.tryCount, maxTryCount.tryCount)
                outputView.printHint(result)
                return
            }

            outputView.printHint(result)
        }
    }

    fun readAnswer(): String {
        return repeatInput {
            val readAnswer = inputView.readAnswer()
            require(wordDictionary.contains(readAnswer)) { "word.txt 내의 단어를 선택해주세요" }
            readAnswer
        }
    }

    fun isGameNotEnd(): Boolean {
        return !maxTryCount.isSame(tryCount)
    }

    private fun <T> repeatInput(input: () -> T): T {
        return runCatching { input() }
            .getOrElse { e ->
                outputView.printErrorMessage(e.message)
                repeatInput(input)
            }
    }

    companion object {
        private const val MAX_TRY_COUNT = 6
    }
}
