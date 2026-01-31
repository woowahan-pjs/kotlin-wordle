package view

import domain.Constant
import domain.vo.GameResult

class ResultView {
    fun printAccumulatedResults(results: List<GameResult>) {
        println()
        results.forEach { result ->
            println(result.emojiArray.joinToString(""))
        }
    }

    fun printFinalScore(attemptCount: Int) {
        println("$attemptCount/${Constant.MAX_GAME_TRY_COUNT}")
    }

}