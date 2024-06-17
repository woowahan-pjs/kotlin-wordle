package wordle.application

import wordle.domain.AnswerWord
import wordle.domain.TodayWord
import wordle.domain.WordleGameLogic
import wordle.view.inputAnswerWord
import java.time.LocalDate

class WordleGame(gameStartDate: LocalDate) {
    private val todayWord = TodayWord(gameStartDate)

    fun play() {
        var tryCount = 6
        while (isContinuousGame(tryCount)) {
            try {
                val answerWord = AnswerWord(inputAnswerWord())
                WordleGameLogic(todayWord, answerWord).compare()
                tryCount--
            } catch (e: IllegalStateException) {
                println("다시 시도 해주세요!")
            }
        }
    }

    private fun isContinuousGame(tryCount: Int) = !isSuccessGame() && tryCount > 0

    private fun isSuccessGame(): Boolean {
        return false
    }
}
