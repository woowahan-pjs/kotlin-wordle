package view

import domain.Constant
import domain.GameString

class InputView {
    fun printIntro() {
        println("WORDLE을 ${Constant.MAX_GAME_TRY_COUNT}번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printInputPrompt() {
        println("정답을 입력해 주세요.")
    }

    fun getInput(): GameString {
        while (true) {
            val inputWord = readLine()!!.trim()
            try {
                return GameString(inputWord)
            } catch (exception: Exception) {
                println(exception.message)
                printInputPrompt()
            }
        }
    }
}