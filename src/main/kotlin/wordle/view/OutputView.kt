package wordle.view

import wordle.domain.Game
import wordle.domain.Tile

object OutputView {

    fun printStartMessage(maxGameCount: Int) = println(
        "WORDLE을 ${maxGameCount}번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다."
    )

    fun printResults(game: Game, isGameOver: Boolean) {
        println()
        if (isGameOver) {
            printCount(game.count, game.maxGameCount)
        }
        game.results.forEach { printResult(it) }
        println()
    }

    private fun printResult(result: List<Tile>) {
        result.forEach { print(it.symbol) }
        println()
    }

    private fun printCount(count: Int, maxGameCount: Int) = println("$count/$maxGameCount\n")
}
