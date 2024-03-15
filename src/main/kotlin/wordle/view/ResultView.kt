package wordle.view

import wordle.domain.Results
import wordle.domain.Tile

private const val MAX_TRY_COUNT = 6

object ResultView {

    fun printInit() {
        println("WORDLE을 ${MAX_TRY_COUNT}번 만에 맞춰 보세요.\n시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printGamePlayCount(index: Int) {
        println("$index /$MAX_TRY_COUNT")
    }

    fun printAllResults(results: Results) {
        results.results.forEach {
            printAllTiles(it.tiles)
            println()
        }
    }

    private fun printAllTiles(tiles: List<Tile>) {
        tiles.forEach { tile ->
            print(tile.viewTile())
        }
    }

    private val Tile.viewTile: () -> String
        get() = {
            when (this) {
                Tile.GREEN -> "\uD83D\uDFE9"
                Tile.YELLOW -> "\uD83D\uDFE8"
                Tile.GRAY -> "⬜"
            }
        }
}
