package wordle.domain

class Results {
    private val _results = mutableListOf<Tiles>()
    val results: List<Tiles>
        get() = _results

    fun combine(newResults: Tiles) {
        _results.add(newResults)
    }
}
