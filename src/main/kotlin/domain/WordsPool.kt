package domain

interface WordsPool {
    fun exists(tiles: Tiles): Boolean

    fun getTodayWords(): Tiles
}
