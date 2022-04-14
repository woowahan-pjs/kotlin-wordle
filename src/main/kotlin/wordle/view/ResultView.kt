package wordle.view

import wordle.domain.Tiles

object ResultView {
    fun printInit() {
        println("WORDLE을 6번 만에 맞춰 보세요.")
        println("시도의 결과는 타일의 색 변화로 나타납니다.")
    }

    fun printGamePlayCount(index: Int) {
        print(index)
        print("/6")
        println()
    }

    fun printAllResults(results: MutableList<Tiles>) {
        results.forEach {
            printAllTiles(it)
            println()
        }
    }

    private fun printAllTiles(tiles: Tiles) {
        tiles.tiles.forEach { tile ->
            print(tile.value)
        }
    }
}
