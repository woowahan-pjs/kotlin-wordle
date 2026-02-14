package wordle.domain

class ResultTiles(val resultTiles: List<Tiles>) {

    constructor(): this(listOf())

    fun combine(newResults: Tiles): ResultTiles = ResultTiles(resultTiles + newResults)
}
