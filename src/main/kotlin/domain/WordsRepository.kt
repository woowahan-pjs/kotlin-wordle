package domain

interface WordsRepository {
    fun exists(tiles: Tiles): Boolean

    fun getTodayWords(): Tiles
}
