package wordle.domain

import java.util.*

private const val LIMIT_SIZE = 6

class Results {

    val value: MutableList<List<Mark>> = mutableListOf()
        get() = Collections.unmodifiableList(field)

    fun add(result: List<Mark>) {
        value.add(result)
    }

    fun isLimit(): Boolean {
        return value.size >= LIMIT_SIZE
    }
}
