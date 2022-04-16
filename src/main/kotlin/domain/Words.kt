package domain

interface Words {
    fun exists(tiles: Tiles): Boolean

    fun getTodayWords(): Tiles
}
