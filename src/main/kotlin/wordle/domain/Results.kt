package wordle.domain

// 불변 보장 필요
class Results {
    private val _results = mutableListOf<Tiles>()
    val results: List<Tiles>
        get() = _results

    // 더 나은 메서드명이 있지 않을까?
    fun combine(newResults: Tiles) {
        _results.add(newResults)
    }
}
