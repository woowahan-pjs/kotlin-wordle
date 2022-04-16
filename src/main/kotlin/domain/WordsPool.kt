package domain

interface WordsPool {
    fun contains(tiles: Tiles): Boolean

    fun getTodayWords(): Tiles
}
