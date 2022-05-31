package wordle.domain

private const val LIMIT_SIZE = 6

class Results {

    val value: MutableList<List<Mark>> = mutableListOf()

    fun add(result: List<Mark>) {
        value.add(result)
    }

    fun isLimit(): Boolean {
        return value.size >= LIMIT_SIZE
    }
}
