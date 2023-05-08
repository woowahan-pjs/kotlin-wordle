package wordle.view

import wordle.domain.MatchResult
import wordle.domain.Result
import wordle.domain.Results

object OutputView {
    fun printGameStart() {
        println("WORDLE을 6번 만에 맞춰 보세요.\n 시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printTrialCount(trialCount: Int, maxTrialCount: Int) {
        println("%d/%d".format(trialCount, maxTrialCount))
    }

    fun printGameResults(results: Results) {
        results.results.forEach { printGameResult(it) }
    }

    private fun printGameResult(result: Result) {
        result.matchResults.forEach { printMatchResult(it) }
        println()
    }

    private fun printMatchResult(matchResult: MatchResult) {
        when (matchResult) {
            MatchResult.GREEN -> print("\uD83D\uDFE9")
            MatchResult.YELLOW -> print("\uD83D\uDFE8")
            MatchResult.GRAY -> print("⬜")
        }
    }

    fun printError(message: String) {
        println(message)
    }
}
