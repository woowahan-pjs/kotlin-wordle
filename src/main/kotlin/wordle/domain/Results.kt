package wordle.domain

private const val LIMIT_SIZE = 6

class Results {

    private val results: MutableList<List<Mark>> = mutableListOf()

    fun add(result: List<Mark>) {
        results.add(result)
    }

    fun findTryCount() =
        results.size

    fun isLimit() =
        results.size >= LIMIT_SIZE

    fun getReadOnlyResults(): List<List<Mark>> =
        results
}
