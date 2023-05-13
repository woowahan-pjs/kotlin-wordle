package view

import wordle.domain.Tile
import wordle.domain.Tile.GREEN
import wordle.domain.Tile.GREY
import wordle.domain.Tile.YELLOW

object OutputView {
    private const val GREEN_TILE_FORMAT = "🟩"
    private const val YELLOW_TILE_FORMAT = "🟨"
    private const val GREY_TILE_FORMAT = "⬜"

    fun printGameStart() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printRequestAnswer() {
        println("정답을 입력해 주세요.")
    }

    fun printError() = println("[ERROR] 다시 시도해주세요🙏")

    fun printResult(gameHistory: List<List<Tile>>) {
        gameHistory.forEach { history -> printRoundResult(history) }
        println()
    }

    private fun printRoundResult(result: List<Tile>) {
        println(result.joinToString("", "", "") { tile -> mapTile(tile) })
    }

    private fun mapTile(tile: Tile) = when (tile) {
        GREEN -> GREEN_TILE_FORMAT
        YELLOW -> YELLOW_TILE_FORMAT
        GREY -> GREY_TILE_FORMAT
    }
}
