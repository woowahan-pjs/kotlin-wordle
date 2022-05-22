package wordle.view

import wordle.domain.Color
import wordle.domain.Colors
import wordle.domain.Game

object OutputView {

    fun printIntroduction(maxCount: Int) {
        println("WORDLE을 $maxCount 번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printResult(colorResults: List<Colors>) {
        for (colorResult in colorResults) {
            colorResult.printColors()
            println()
        }
    }

    private fun Colors.printColors() {
        this.values.forEach {
            print(
                when (it) {
                    Color.GREEN -> "🟩"
                    Color.YELLOW -> "🟨"
                    Color.GRAY -> "⬜"
                }
            )
        }
    }

    fun printCount(tryCount: Int) {
        println("$tryCount/${Game.maxCount}")
    }

    fun printError(message: String?) {
        println(message)
    }
}
