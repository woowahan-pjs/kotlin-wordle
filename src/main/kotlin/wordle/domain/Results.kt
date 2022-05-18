package wordle.domain

private const val LIMIT_SIZE = 6

class Results {

    val value: MutableList<List<Mark>> = mutableListOf()

    fun add(result: MutableList<Mark>) {
        value.add(result)
    }

    fun findTryCount() =
        value.size

    fun isLimit() =
        value.size >= LIMIT_SIZE
}
