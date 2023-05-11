package wordle.view

import wordle.domain.GameResult
import wordle.domain.Tiles

object OutputView {

    fun printInitMessage(maxRound: Int) {
        println("WORDLE을 ${maxRound}번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printGameResult(isOver: Boolean, count: Int, maxRound: Int, gameResult: GameResult) {
        if (isOver) {
            println("$count/$maxRound")
        }
        println()
        gameResult.gameResult
            .forEach {
                printTiles(it)
                println()
            }
    }

    private fun printTiles(tiles: Tiles) {
        tiles.tiles
            .forEach { print(it.symbol) }
    }

    fun printErrorMessage(message: String?) {
        println(message)
    }
}
