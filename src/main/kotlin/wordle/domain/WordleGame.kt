package wordle.domain

import wordle.view.inputAnswer
import wordle.view.printFail
import wordle.view.printResult
import wordle.view.printRetry
import wordle.view.printSuccess

class WordleGame {
    private val results = WordResults()

    fun play(todayWord: TodayWord) {
        val wordleGameLogic = WordleGameLogic(todayWord)

        while (results.isContinuousGame()) {
            val answer = inputAnswer()
            try {
                val answerWord = Word(answer)
                val result = wordleGameLogic.compare(answerWord)
                results.addResults(result)
                printResult(results)
            } catch (e: IllegalStateException) {
                printRetry(e.message)
            }
        }
        gameResult(todayWord)
    }

    private fun gameResult(todayWord: Word) {
        if (results.isSuccessGame()) {
            printSuccess(results.attemptCount)
            return
        }
        printFail(todayWord)
    }
}
