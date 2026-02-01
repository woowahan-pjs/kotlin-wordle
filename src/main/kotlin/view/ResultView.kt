package view

import domain.vo.GameResult

class ResultView {

    fun printResult(gameResult: GameResult) {
        val resultString = gameResult.emojiArray.joinToString("")
        println(resultString)
        if (gameResult.isSuccess) {
            println("성공입니다!")
        }
    }
}