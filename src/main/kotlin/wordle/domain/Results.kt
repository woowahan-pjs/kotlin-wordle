package wordle.domain

private const val LIMIT_SIZE = 6

class Results {

    private val _results: MutableList<List<Mark>> = mutableListOf()
    val results: List<List<Mark>>
        get() = _results

    fun add(result: List<Mark>) {
        _results.add(result)
    }

    fun findTryCount() =
        _results.size

    fun isLimit() =
        _results.size >= LIMIT_SIZE
}
