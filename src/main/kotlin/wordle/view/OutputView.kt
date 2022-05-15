package wordle.view

import wordle.domain.Game
import wordle.domain.Tile

object OutputView {

    fun printStartMessage() = println(
        "WORDLE을 ${Game.MAX_GAME_COUNT}번 만에 맞춰 보세요.\n" + "시도의 결과는 타일의 색 변화로 나타납니다."
    )

    fun printResults(results: List<List<Tile>>) {
        println()
        results.forEach { printResult(it) }
        println()
    }

    private fun printResult(result: List<Tile>) {
        result.forEach { print(it.symbol) }
        println()
    }

    fun printCount(count: Int) = println("\n$count/${Game.MAX_GAME_COUNT}")
}
