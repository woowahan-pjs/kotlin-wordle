package wordle.view

import wordle.domain.Tile
import wordle.domain.Tiles

private const val MAX_TRY_COUNT = 6

object ResultView {
    fun printInit() {
        println("WORDLE을 ${MAX_TRY_COUNT}번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printGamePlayCount(index: Int) {
        print(index)
        print("/$MAX_TRY_COUNT")
        println()
    }

    fun printAllResults(results: MutableList<Tiles>) {
        results.forEach {
            printAllTiles(it)
            println()
        }
    }

    private fun printAllTiles(tiles: Tiles) {
        tiles.forEach { tile ->
            print(viewTile(tile))
        }
    }

    private fun viewTile(tile: Tile) = when (tile) {
        Tile.GREEN -> "\uD83D\uDFE9"
        Tile.YELLOW -> "\uD83D\uDFE8"
        Tile.GRAY -> "⬜"
    }
}
