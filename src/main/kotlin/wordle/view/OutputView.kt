package wordle.view

import wordle.domain.TileColor.*
import wordle.domain.WordleGameResult

class OutputView {

    fun printStartMessage() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printResults(wordleGameResults: List<WordleGameResult>) {
        wordleGameResults.forEach { result ->
            result.printLine()
        }
    }

    private fun WordleGameResult.printLine() {
        this.result.forEach { tileColor ->
            print(
                when (tileColor) {
                    GREEN -> "🟩"
                    YELLOW -> "🟨"
                    GRAY -> "⬜"
                }
            )
        }
        println()
    }
}
