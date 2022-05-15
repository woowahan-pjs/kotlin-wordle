package wordle.view

import wordle.domain.Answer
import wordle.domain.Color

object OutputView {
    private val results: MutableList<List<Color>> = mutableListOf()

    fun printIntroduction() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printResult(newResult: List<Color>) {
        results.add(newResult)
        for (result in results) {
            result.forEach { print(it.representation) }
            println()
        }
        println()
    }

    fun printAnswer(answer: Answer) {
        println("아쉽습니다! 정답은 ${answer.getAnswer()}입니다.")
    }

    fun printCount(tryCount: Int) {
        println("$tryCount/6")
    }

    fun printError(message: String?) {
        println(message)
    }
}
