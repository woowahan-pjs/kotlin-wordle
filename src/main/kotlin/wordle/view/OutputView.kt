package wordle.view

import wordle.domain.Game
import wordle.domain.Tile

object OutputView {

    fun printStartMessage(game: Game) = println(
        """
            |WORDLE을 ${game.totalCount}번 만에 맞춰 보세요.
            |시도의 결과는 타일의 색 변화로 나타납니다.
        """.trimMargin()
    )

    fun printResults(game: Game, isGameOver: Boolean) {
        if (isGameOver) {
            printCount(game.count, game.totalCount)
        }
        println()
        game.results.forEach { printResult(it) }
        println()
    }

    private fun printResult(result: List<Tile>) {
        result.forEach { print(it.symbol) }
        println()
    }

    private fun printCount(count: Int, totalRound: Int) = println(
        """
            |
            |$count/$totalRound
        """.trimMargin()
    )
}
