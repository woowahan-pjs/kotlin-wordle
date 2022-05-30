package wordle.view

import wordle.domain.Game
import wordle.domain.Tile

object OutputView {

    fun printStartMessage(maxGameCount: Int) = println(
        """
            |WORDLE을 ${maxGameCount}번 만에 맞춰 보세요.
            |시도의 결과는 타일의 색 변화로 나타납니다.
        """.trimMargin()
    )

    fun printResults(game: Game) {
        if (game.isGameOver) {
            printCount(game.count, game.maxGameCount)
        }
        println(game.results.joinToString(separator = "\n", prefix = "\n") { it.toSymbols() })
    }

    private fun printCount(count: Int, maxGameCount: Int) = println("\n$count/$maxGameCount")

    private fun List<Tile>.toSymbols() = joinToString(separator = "") { it.symbol }
}
