package wordle.application

import wordle.domain.TodayWord
import wordle.domain.Word
import wordle.domain.WordResults
import wordle.domain.WordleGameLogic
import wordle.view.inputAnswerWord
import wordle.view.printFail
import wordle.view.printRetry
import wordle.view.printSuccess
import wordle.view.printWordResults
import java.time.LocalDate

class WordleGame(gameStartDate: LocalDate) {
    private val todayWord = TodayWord(gameStartDate)
    private val wordleGameLogic = WordleGameLogic(todayWord)
    private val results = WordResults()

    fun play() {
        while (results.isContinuousGame()) {
            try {
                val answerWord = Word(inputAnswerWord())
                val result = wordleGameLogic.compare(answerWord)
                results.addResults(result)
                printWordResults(results)
            } catch (e: IllegalStateException) {
                printRetry(e.message)
            }
        }
        printGameResult()
    }

    private fun printGameResult() {
        if (results.isSuccessfulGame()) {
            printSuccess(results.attemptCount)
            return
        }
        printFail(todayWord)
    }
}
